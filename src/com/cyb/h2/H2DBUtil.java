package com.cyb.h2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyb.diffcult.内部类;
import com.cyb.diffcult.内部类.Inner1;

public class H2DBUtil {
	static Logger log = LoggerFactory.getLogger(H2DBUtil.class);
	private static String embedPrix = "jdbc:h2:";
	private static String tcpPrix = "jdbc:h2:tcp://localhost/";
	private static String memPrix = "jdbc:h2:tcp://localhost/mem:";
	private static String dbPath = "/root/data/testdb";//  /root/data/trade d:/data/
	static {
		H2Manager.start();
	}
	public static void main(String[] args) {
		new Inner1();
		H2Manager.start();H2Manager.start();
		H2DBUtil.testFileConnection();
		H2DBUtil.testTCPConnection();
		H2DBUtil.testMemConnection();
		System.exit(0);
	}
   public static void testFileConnection(){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(embedPrix+dbPath+"test1", "sa", "123454");
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT 1+1 FROM dual ");   
		  while(rs.next()) {   
			  log.info("Ƕ��ģʽ��1+1="+rs.getInt(1));
		  }
		  conn.close();
		  log.info("H2DB File test Success!");
	} catch (Exception e) {
		System.out.println("tcp File test err!");
	}
   }
   public static Connection getConnection(){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(embedPrix+dbPath+"test1", "sa", "123454");
		   return conn;
	   } catch (Exception e) {
			System.out.println("tcp File test err!");
			return null;
		}
	  
   }
   public static void testTCPConnection(){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(tcpPrix+dbPath+"test2", "sa", "123455");
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT 1+1 FROM dual ");   
		  while(rs.next()) {   
			  log.info("serverģʽ��1+1="+rs.getInt(1));
		  }
		  conn.close();
		  log.info("H2DB TCP test Success!");
		} catch (Exception e) {
			log.info("tcp TCP test err!");
		} 
   }
   public static void testMemConnection(){
	   try {
		   //Class.forName("org.h2.Driver");
		   Connection conn = DriverManager.getConnection(memPrix+dbPath+"test3", "sa", "123456");
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery("SELECT 1+1 FROM dual ");   
		  while(rs.next()) {   
			  log.info("Memģʽ��1+1="+rs.getInt(1));
		  }
		  conn.close();
		  log.info("H2DB Mem test Success!");
		} catch (Exception e) {
			log.info("tcp Mem test err!");
		} 
   }
   
   
}