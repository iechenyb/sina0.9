package com.cyb.jndi;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
//http://www.cnblogs.com/chen-lhx/p/5753520.html
public class JndiMain {
	//无法测试，因为使用jndi需要在tomcat容器中进行！
  public static void main(String[] args) {
	  Connection conn=null;
	  try {
		  //oracleDataSource====》jdbc/oracle
	     Context ctx=new InitialContext();
	     Object datasourceRef=ctx.lookup("java:comp/env/jdbc/oracleDataSource"); //引用数据源
	     DataSource ds=(DataSource)datasourceRef;
	     conn=ds.getConnection();
	     System.out.println("$$"+conn);
	     /* 使用conn进行数据库SQL操作 */
	    // c.close();
	  }
	  catch(Exception e) {
	     e.printStackTrace();
	  }
	  finally {
	     if(conn!=null) {
	       try {
	         conn.close();
	       } catch(Exception e) { }
	     }
	  }
}
}
