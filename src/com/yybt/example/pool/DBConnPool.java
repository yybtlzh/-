package com.yybt.example.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 数据库连接池
 * 
 * @author liuzehong
 *
 */
class DBConnPool {

	/** 数据库 链接URL地址 **/
	private static String url;
	/** 账号 **/
	private static String username;
	/** 密码 **/
	private static String password;
	/** 初始连接数 **/
	private static int initialSize = 20;
	/** 最大活动连接数 **/
	private static int maxActive = 63;
	/** 最小闲置连接数 **//*
					 * private static final int minIdle=10;
					 */
	/** 连接耗尽时最大等待获取连接时间 **/
	private static final long maxWait = 10000;
	// 数据库驱动
	private static String jdbcDriver = "";
	// 连接池自动增加的大小
	private static int incrementalConnections = 10;
	// 存放连接池中数据库连接的向量 , 初始时为 null
	private static Vector<PooledConnection> connections = null;

	static {
		init();
	}

	/**
	 * 温馨提示：是个合格的程序员，就别删。懂的人自然懂。
	 */
	private DBConnPool() {
	}

	private static void init() {
		jdbcDriver = "com.ibmxxxxxx";
		url = "jdbc:xxxxxxxxxx";
		username = "root";
		password = "123456";
		connections = new Vector<PooledConnection>(initialSize);
		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回连接池的初始大小
	 * 
	 * @return
	 */
	public static int initialSize() {
		return initialSize;
	}

	/**
	 * 设置连接池的初始大小
	 * 
	 * @param 用于设置初始连接池中连接的数量
	 */

	public static void setInitialConnections(int size) {
		initialSize = size;
	}

	/**
	 * 返回连接池自动增加的大小 、
	 *
	 * @return 连接池自动增加的大小
	 */
	public static int getIncrementalStep() {
		return incrementalConnections;
	}

	/**
	 * 设置连接池自动增加的大小
	 * 
	 * @param 连接池自动增加的大小
	 */

	public static void setIncrementalStep(int step) {
		incrementalConnections = step;
	}

	/**
	 * 返回连接池中最大的可用连接数量
	 * 
	 * @return 连接池中最大的可用连接数量
	 */

	public static int getMaxConnections() {
		return maxActive;
	}

	/**
	 * 设置连接池中最大可用的连接数量
	 *
	 * @param 设置连接池中最大可用的连接数量值
	 */

	public void setMaxConnections(int maxConnections) {
		maxActive = maxConnections;
	}

	/**
	 * 创建由 numConnections 指定数目的数据库连接 , 并把这些连接 放入 connections 向量中 getConnection
	 * 
	 * @param numConnections
	 *            要创建的数据库连接的数目
	 */

	private static void createConnections(int numConnections) throws SQLException {
		// 循环创建指定数目的数据库连接
		for (int x = 0; x < numConnections; x++) {
			if (maxActive > 0 && connections.size() >= maxActive) {
				break;
			}
			try {
				connections.addElement(new PooledConnection(newConnection()));
			} catch (SQLException e) {
				System.out.println("创建数据库连接失败!原因是:" + e.getMessage());
				throw new SQLException();
			}
		}
	}

	/**
	 * 创建一个新的数据库连接并返回它
	 *
	 * @return 返回一个新创建的数据库连接
	 */

	private static Connection newConnection() throws SQLException {
		// 创建一个数据库连接
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection; // 返回创建的新的数据库连接
	}

	public static Connection getConnection() {
		return getConn();
	}

	private static synchronized Connection getConn() {
		try {
			// 确保连接池己被创建
			if (connections == null || connections.size() < 1) {
				System.out.println("刘泽宏：初始化连接");
				createConnections(initialSize);
				// 连接池还没创建，则返回 null
			}
			Connection conn = getFreeConnection(); // 获得一个可用的数据库连接
			// 如果目前没有可以使用的连接，即所有的连接都在使用中
			long currentTimeMillis = System.currentTimeMillis();
			while (System.currentTimeMillis() - currentTimeMillis < maxWait && conn == null) {
				// 等待250毫秒重新获取
				wait(250);
				conn = getFreeConnection();
			}
			return conn;// 返回获得的可用的连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return null;
	}

	/**
	 * 本函数从连接池向量 connections 中返回一个可用的的数据库连接，如果 当前没有可用的数据库连接，本函数则根据
	 * incrementalConnections 设置 的值创建几个数据库连接，并放入连接池中。 如果创建后，所有的连接仍都在使用中，则返回 null
	 * 
	 * @return 返回一个可用的数据库连接
	 */
	private static Connection getFreeConnection() throws SQLException {
		// 从连接池中获得一个可用的数据库连接
		Connection conn = findFreeConnection();
		if (conn == null) {
			// 如果目前连接池中没有可用的连接
			// 创建一些连接
			createConnections(incrementalConnections);
			// 重新从池中查找是否有可用连接
			conn = findFreeConnection();
			// 可能还是null，无所谓，直接返回就成
		}
		return conn;
	}

	/**
	 * 查找连接池中所有的连接，查找一个可用的数据库连接， 如果没有可用的连接，返回 null
	 *
	 * @return 返回一个可用的数据库连接
	 */

	private static Connection findFreeConnection() throws SQLException {
		Connection conn = null;
		PooledConnection pConn = null;
		// 获得连接池向量中所有的对象
		Enumeration<PooledConnection> enumerate = connections.elements();
		// 遍历所有的对象，看是否有可用的连接
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			if (!pConn.isBusy()) {
				// 如果此对象不忙，则获得它的数据库连接并把它设为忙
				conn = pConn.getConnection();
				pConn.setBusy(true);
				// 测试此连接是否可用
				// pConn.setConnection(conn);
				break; // 己经找到一个可用的连接，退出
			}
		}
		return conn;// 返回找到到的可用连接
	}

	/**
	 * 此函数返回一个数据库连接到连接池中，并把此连接置为空闲。 所有使用连接池获得的数据库连接均应在不使用此连接时返回它。
	 * 
	 * @param 需返回到连接池中的连接对象
	 */

	protected static void returnConnection(Connection conn) {
		// 确保连接池存在，如果连接没有创建（不存在），直接返回
		if (connections == null) {
			System.out.println(" 连接池不存在，无法返回此连接到连接池中 !");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		// 遍历连接池中的所有连接，找到这个要返回的连接对象
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			// 先找到连接池中的要返回的连接对象
			if (conn == pConn.getConnection()) {
				// 找到 ,设置此连接为空闲状态
				pConn.setBusy(false);
				break;
			}
		}
	}

	/**
	 * 刷新连接池中所有的连接对象
	 *
	 */

	protected static synchronized void refreshConnections() throws SQLException {
		// 确保连接池己创新存在
		if (connections == null) {
			System.out.println(" 连接池不存在，无法刷新 !");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		while (enumerate.hasMoreElements()) {
			// 获得一个连接对象
			pConn = (PooledConnection) enumerate.nextElement();
			// 如果对象忙则等 5 秒 ,5 秒后直接刷新
			if (pConn.isBusy()) {
				wait(5000); // 等 5 秒
			}
			// 关闭此连接，用一个新的连接代替它。
			closeConnection(pConn.getConnection());
			pConn.setConnection(newConnection());
			pConn.setBusy(false);
		}
	}

	/**
	 * 关闭连接池中所有的连接，并清空连接池。
	 */

	public synchronized void closeConnectionPool() throws SQLException {
		// 确保连接池存在，如果不存在，返回
		if (connections == null) {
			System.out.println(" 连接池不存在，无法关闭 !");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			// 如果忙，等 5 秒
			if (pConn.isBusy()) {
				wait(5000); // 等 5 秒
			}
			// 5 秒后直接关闭它
			closeConnection(pConn.getConnection());
			// 从连接池向量中删除它
			connections.removeElement(pConn);
		}
		// 置连接池为空
		connections = null;
	}

	/**
	 * 关闭一个数据库连接
	 *
	 * @param 需要关闭的数据库连接
	 */

	private static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(" 关闭数据库连接出错： " + e.getMessage());
		}
	}

	/**
	 * 使程序等待给定的毫秒数
	 *
	 * @param 给定的毫秒数
	 */

	private static void wait(int mSeconds) {
		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e) {
		}
	}

	/**
	 *
	 * 内部使用的用于保存连接池中连接对象的类 此类中有两个成员，一个是数据库的连接，另一个是指示此连接是否 正在使用的标志。
	 */

	static class PooledConnection {
		Connection connection = null;// 数据库连接
		boolean busy = false; // 此连接是否正在使用的标志，默认没有正在使用

		// 构造函数，根据一个 Connection 构告一个 PooledConnection 对象
		public PooledConnection(Connection connection) {
			this.connection = connection;
		}

		// 返回此对象中的连接
		public Connection getConnection() {
			return connection;
		}

		// 设置此对象的，连接
		public void setConnection(Connection connection) {
			this.connection = connection;
		}

		// 获得对象连接是否忙
		public boolean isBusy() {
			return busy;
		}

		// 设置对象的连接正在忙
		public void setBusy(boolean busy) {
			this.busy = busy;
		}
	}

}