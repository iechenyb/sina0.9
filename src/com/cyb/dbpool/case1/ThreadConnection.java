package com.cyb.dbpool.case1;
import java.sql.Connection;
import java.sql.SQLException;
/** 
 * 模拟线程启动，去获得连接 
 * @author Ran 
 * 
 */  
public class ThreadConnection implements Runnable{  
    private IConnectionPool pool;  
    private String name;
    public ThreadConnection(String name){
    	this.name = name;
    }
    @Override  
    public void run() {  
        pool = ConnectionPoolManager.getInstance().getPool(name);  
    }  
      
    public Connection getConnection(){  
        Connection conn = null;  
        if(pool != null && pool.isActive()){  
            conn = pool.getConnection();  
        }  
        return conn;  
    } 
    public synchronized void realse(Connection e){  
         try {
        	 if(e!=null&&!e.isClosed()){
        		 pool.releaseConn(e);
			 }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    }  
      
    public Connection getCurrentConnection(){  
        Connection conn = null;  
        if(pool != null && pool.isActive()){  
            conn = pool.getCurrentConnecton();  
        }  
        return conn;  
    }  
} 