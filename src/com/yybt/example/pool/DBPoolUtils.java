package com.yybt.example.pool;

import java.sql.Connection;
import java.sql.SQLException;

public class DBPoolUtils {
	
	public static Connection getConnection()  {
		return DBConnPool.getConnection();
	}

   /**
    * �˺�������һ�����ݿ����ӵ����ӳ��У����Ѵ�������Ϊ���С� 
    * ����ʹ�����ӳػ�õ����ݿ����Ӿ�Ӧ�ڲ�ʹ�ô�����ʱ��������
    * @param �践�ص����ӳ��е����Ӷ���
    */
   public static void returnConnection(Connection conn) {
	   DBConnPool.returnConnection(conn);
   }
   
   
   
   public static void refreshConnections(Connection conn) throws SQLException {
	   DBConnPool.refreshConnections();
   }

}
