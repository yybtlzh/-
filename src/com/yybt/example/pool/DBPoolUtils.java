package com.yybt.example.pool;

import java.sql.Connection;
import java.sql.SQLException;

public class DBPoolUtils {
	
	public static Connection getConnection()  {
		return DBConnPool.getConnection();
	}

   /**
    * 此函数返回一个数据库连接到连接池中，并把此连接置为空闲。 
    * 所有使用连接池获得的数据库连接均应在不使用此连接时返回它。
    * @param 需返回到连接池中的连接对象
    */
   public static void returnConnection(Connection conn) {
	   DBConnPool.returnConnection(conn);
   }
   
   
   
   public static void refreshConnections(Connection conn) throws SQLException {
	   DBConnPool.refreshConnections();
   }

}
