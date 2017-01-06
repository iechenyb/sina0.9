package com.cyb.shejimoshi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class 哼元模式 {
	public static void main(String[] args) {
		ConnectionPool pool = new ConnectionPool();
		for(int i=1;i<12;i++){
			System.out.println(i+"st:"+pool.getConnection());
		}
	}
}
class ConnectionPool {
    private Vector<Connection> pool;  
    /*公有属性*/  
    private String url = "jdbc:h2:tcp://localhost/~";  
    private String username = "root";  
    private String password = "root";  
    private String driverClassName = "org.h2.Driver";  
  
    private int poolSize = 10;  
    private static ConnectionPool instance = null;  
    Connection conn = null;  
  
    /*构造方法，做一些初始化工作*/  
    ConnectionPool() {  
    	initPools();
    }  
    
    public void initPools(){
    	pool = new Vector<Connection>(poolSize);  
        for (int i = 0; i < poolSize; i++) {  
            try {  
                Class.forName(driverClassName);  
                conn = DriverManager.getConnection(url, username, password);  
                pool.add(conn);  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }
  
    /* 返回连接到连接池 */  
    public synchronized void release() {  
        pool.add(conn);  
    }  
  
    /* 返回连接池中的一个数据库连接 */  
    public synchronized Connection getConnection() {  
    	if(pool.size()<=0){
    		initPools();
    	}
        if (pool.size() > 0){  
            Connection conn = pool.get(0);  
            System.out.println("从连接池里获取连接！"+conn);
            pool.remove(conn);  
            return conn;  
        }else{
        	System.out.println("连接池里无连接可用！");
        	return null;
        } 
    }  
}  
