package com.yybt.example.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

/**
 * ���ݿ����ӳ�
 * 
 * @author liuzehong
 *
 */
class DBConnPool {

	/** ���ݿ� ����URL��ַ **/
	private static String url;
	/** �˺� **/
	private static String username;
	/** ���� **/
	private static String password;
	/** ��ʼ������ **/
	private static int initialSize = 20;
	/** ��������� **/
	private static int maxActive = 63;
	/** ��С���������� **//*
					 * private static final int minIdle=10;
					 */
	/** ���Ӻľ�ʱ���ȴ���ȡ����ʱ�� **/
	private static final long maxWait = 10000;
	// ���ݿ�����
	private static String jdbcDriver = "";
	// ���ӳ��Զ����ӵĴ�С
	private static int incrementalConnections = 10;
	// ������ӳ������ݿ����ӵ����� , ��ʼʱΪ null
	private static Vector<PooledConnection> connections = null;

	static {
		init();
	}

	/**
	 * ��ܰ��ʾ���Ǹ��ϸ�ĳ���Ա���ͱ�ɾ����������Ȼ����
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
	 * �������ӳصĳ�ʼ��С
	 * 
	 * @return
	 */
	public static int initialSize() {
		return initialSize;
	}

	/**
	 * �������ӳصĳ�ʼ��С
	 * 
	 * @param �������ó�ʼ���ӳ������ӵ�����
	 */

	public static void setInitialConnections(int size) {
		initialSize = size;
	}

	/**
	 * �������ӳ��Զ����ӵĴ�С ��
	 *
	 * @return ���ӳ��Զ����ӵĴ�С
	 */
	public static int getIncrementalStep() {
		return incrementalConnections;
	}

	/**
	 * �������ӳ��Զ����ӵĴ�С
	 * 
	 * @param ���ӳ��Զ����ӵĴ�С
	 */

	public static void setIncrementalStep(int step) {
		incrementalConnections = step;
	}

	/**
	 * �������ӳ������Ŀ�����������
	 * 
	 * @return ���ӳ������Ŀ�����������
	 */

	public static int getMaxConnections() {
		return maxActive;
	}

	/**
	 * �������ӳ��������õ���������
	 *
	 * @param �������ӳ��������õ���������ֵ
	 */

	public void setMaxConnections(int maxConnections) {
		maxActive = maxConnections;
	}

	/**
	 * ������ numConnections ָ����Ŀ�����ݿ����� , ������Щ���� ���� connections ������ getConnection
	 * 
	 * @param numConnections
	 *            Ҫ���������ݿ����ӵ���Ŀ
	 */

	private static void createConnections(int numConnections) throws SQLException {
		// ѭ������ָ����Ŀ�����ݿ�����
		for (int x = 0; x < numConnections; x++) {
			if (maxActive > 0 && connections.size() >= maxActive) {
				break;
			}
			try {
				connections.addElement(new PooledConnection(newConnection()));
			} catch (SQLException e) {
				System.out.println("�������ݿ�����ʧ��!ԭ����:" + e.getMessage());
				throw new SQLException();
			}
		}
	}

	/**
	 * ����һ���µ����ݿ����Ӳ�������
	 *
	 * @return ����һ���´��������ݿ�����
	 */

	private static Connection newConnection() throws SQLException {
		// ����һ�����ݿ�����
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection; // ���ش������µ����ݿ�����
	}

	public static Connection getConnection() {
		return getConn();
	}

	private static synchronized Connection getConn() {
		try {
			// ȷ�����ӳؼ�������
			if (connections == null || connections.size() < 1) {
				System.out.println("����꣺��ʼ������");
				createConnections(initialSize);
				// ���ӳػ�û�������򷵻� null
			}
			Connection conn = getFreeConnection(); // ���һ�����õ����ݿ�����
			// ���Ŀǰû�п���ʹ�õ����ӣ������е����Ӷ���ʹ����
			long currentTimeMillis = System.currentTimeMillis();
			while (System.currentTimeMillis() - currentTimeMillis < maxWait && conn == null) {
				// �ȴ�250�������»�ȡ
				wait(250);
				conn = getFreeConnection();
			}
			return conn;// ���ػ�õĿ��õ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		return null;
	}

	/**
	 * �����������ӳ����� connections �з���һ�����õĵ����ݿ����ӣ���� ��ǰû�п��õ����ݿ����ӣ������������
	 * incrementalConnections ���� ��ֵ�����������ݿ����ӣ����������ӳ��С� ������������е������Զ���ʹ���У��򷵻� null
	 * 
	 * @return ����һ�����õ����ݿ�����
	 */
	private static Connection getFreeConnection() throws SQLException {
		// �����ӳ��л��һ�����õ����ݿ�����
		Connection conn = findFreeConnection();
		if (conn == null) {
			// ���Ŀǰ���ӳ���û�п��õ�����
			// ����һЩ����
			createConnections(incrementalConnections);
			// ���´ӳ��в����Ƿ��п�������
			conn = findFreeConnection();
			// ���ܻ���null������ν��ֱ�ӷ��ؾͳ�
		}
		return conn;
	}

	/**
	 * �������ӳ������е����ӣ�����һ�����õ����ݿ����ӣ� ���û�п��õ����ӣ����� null
	 *
	 * @return ����һ�����õ����ݿ�����
	 */

	private static Connection findFreeConnection() throws SQLException {
		Connection conn = null;
		PooledConnection pConn = null;
		// ������ӳ����������еĶ���
		Enumeration<PooledConnection> enumerate = connections.elements();
		// �������еĶ��󣬿��Ƿ��п��õ�����
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			if (!pConn.isBusy()) {
				// ����˶���æ�������������ݿ����Ӳ�������Ϊæ
				conn = pConn.getConnection();
				pConn.setBusy(true);
				// ���Դ������Ƿ����
				// pConn.setConnection(conn);
				break; // �����ҵ�һ�����õ����ӣ��˳�
			}
		}
		return conn;// �����ҵ����Ŀ�������
	}

	/**
	 * �˺�������һ�����ݿ����ӵ����ӳ��У����Ѵ�������Ϊ���С� ����ʹ�����ӳػ�õ����ݿ����Ӿ�Ӧ�ڲ�ʹ�ô�����ʱ��������
	 * 
	 * @param �践�ص����ӳ��е����Ӷ���
	 */

	protected static void returnConnection(Connection conn) {
		// ȷ�����ӳش��ڣ��������û�д����������ڣ���ֱ�ӷ���
		if (connections == null) {
			System.out.println(" ���ӳز����ڣ��޷����ش����ӵ����ӳ��� !");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		// �������ӳ��е��������ӣ��ҵ����Ҫ���ص����Ӷ���
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			// ���ҵ����ӳ��е�Ҫ���ص����Ӷ���
			if (conn == pConn.getConnection()) {
				// �ҵ� ,���ô�����Ϊ����״̬
				pConn.setBusy(false);
				break;
			}
		}
	}

	/**
	 * ˢ�����ӳ������е����Ӷ���
	 *
	 */

	protected static synchronized void refreshConnections() throws SQLException {
		// ȷ�����ӳؼ����´���
		if (connections == null) {
			System.out.println(" ���ӳز����ڣ��޷�ˢ�� !");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		while (enumerate.hasMoreElements()) {
			// ���һ�����Ӷ���
			pConn = (PooledConnection) enumerate.nextElement();
			// �������æ��� 5 �� ,5 ���ֱ��ˢ��
			if (pConn.isBusy()) {
				wait(5000); // �� 5 ��
			}
			// �رմ����ӣ���һ���µ����Ӵ�������
			closeConnection(pConn.getConnection());
			pConn.setConnection(newConnection());
			pConn.setBusy(false);
		}
	}

	/**
	 * �ر����ӳ������е����ӣ���������ӳء�
	 */

	public synchronized void closeConnectionPool() throws SQLException {
		// ȷ�����ӳش��ڣ���������ڣ�����
		if (connections == null) {
			System.out.println(" ���ӳز����ڣ��޷��ر� !");
			return;
		}
		PooledConnection pConn = null;
		Enumeration<PooledConnection> enumerate = connections.elements();
		while (enumerate.hasMoreElements()) {
			pConn = (PooledConnection) enumerate.nextElement();
			// ���æ���� 5 ��
			if (pConn.isBusy()) {
				wait(5000); // �� 5 ��
			}
			// 5 ���ֱ�ӹر���
			closeConnection(pConn.getConnection());
			// �����ӳ�������ɾ����
			connections.removeElement(pConn);
		}
		// �����ӳ�Ϊ��
		connections = null;
	}

	/**
	 * �ر�һ�����ݿ�����
	 *
	 * @param ��Ҫ�رյ����ݿ�����
	 */

	private static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(" �ر����ݿ����ӳ��� " + e.getMessage());
		}
	}

	/**
	 * ʹ����ȴ������ĺ�����
	 *
	 * @param �����ĺ�����
	 */

	private static void wait(int mSeconds) {
		try {
			Thread.sleep(mSeconds);
		} catch (InterruptedException e) {
		}
	}

	/**
	 *
	 * �ڲ�ʹ�õ����ڱ������ӳ������Ӷ������ ��������������Ա��һ�������ݿ�����ӣ���һ����ָʾ�������Ƿ� ����ʹ�õı�־��
	 */

	static class PooledConnection {
		Connection connection = null;// ���ݿ�����
		boolean busy = false; // �������Ƿ�����ʹ�õı�־��Ĭ��û������ʹ��

		// ���캯��������һ�� Connection ����һ�� PooledConnection ����
		public PooledConnection(Connection connection) {
			this.connection = connection;
		}

		// ���ش˶����е�����
		public Connection getConnection() {
			return connection;
		}

		// ���ô˶���ģ�����
		public void setConnection(Connection connection) {
			this.connection = connection;
		}

		// ��ö��������Ƿ�æ
		public boolean isBusy() {
			return busy;
		}

		// ���ö������������æ
		public void setBusy(boolean busy) {
			this.busy = busy;
		}
	}

}