package com.yybt.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import com.yybt.example.pool.DBPoolUtils;
/**
 * 需要在连接池DBConnPool里配置下连接参数
 * @author liuzehong
 *
 */

public class MysqlMillions {
	
	
	  public void insert(){
	    Connection conn = null;
	    PreparedStatement pstm =null;
	    try {
	      Class.forName("com.mysql.jdbc.Driver");
	      conn = DBPoolUtils.getConnection();    
	      String sql = "INSERT INTO usertable (uname,upassword,uphone) VALUES(?,?,?)";
	      pstm = conn.prepareStatement(sql);
	      Random rand = new Random();
	      int a,b,c,d;
	      for (int i = 1; i <= 2000; i++) {
	          pstm.setString(1, "张三"+i );
	          a = rand.nextInt(10);
	          b = rand.nextInt(10);
	          c = rand.nextInt(10);
	          d = rand.nextInt(10);
	          pstm.setString(2, "1234"+a+b+c+d);
	          pstm.setString(3, "188"+a+"88"+b+c+"66"+d);
	         pstm.addBatch();
	         System.out.println(i);
	      }
	      System.out.println(sql);
	      pstm.executeBatch();
	    } catch (Exception e) {
	      e.printStackTrace();
	      throw new RuntimeException(e);
	    }finally{
	      if(pstm!=null){
	        try {
	          pstm.close();
	        } catch (SQLException e) {
	          e.printStackTrace();
	          throw new RuntimeException(e);
	        }
	      }
	      if(conn!=null){
	        try {
	          conn.close();
	        } catch (SQLException e) {
	          e.printStackTrace();
	          throw new RuntimeException(e);
	        }
	      }
	    }
	  }

	public static void main(String[] args) {
		MysqlMillions test=new MysqlMillions();
		  long a=System.currentTimeMillis();
		  System.out.println();
		  test.insert();
		  long b=System.currentTimeMillis();
		  System.out.println((long)(b-a));
	}
}
