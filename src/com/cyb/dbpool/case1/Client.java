package com.cyb.dbpool.case1;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.cyb.h2.H2DBUtil;

public class Client {  
	public static String poolName = "h2Pool";
    public static void main(String[] args) throws InterruptedException {  
        // 初始化连接池  
        Thread t = init(poolName);  
        t.start();  
        t.join();  
        List<ThreadConnection> list = new ArrayList<ThreadConnection>();  
        for(int i=0;i<1;i++){
        	ThreadConnection a = new ThreadConnection(poolName);  
        	list.add(a);
        	Thread t1 = new Thread(a); 
        	t1.setPriority(10);  
        	t1.start();  
        }
        
        /*从多个连接池里拿数据连接
         * int i =0 ;
         * for(ThreadConnection a:list){
        	 Connection e = a.getConnection();
        	 System.out.println("获取连接"+a+"-> "+e);
        	if(i++>=50){
        		a.realse(e);
        	}
        }*/ 
        /**
         * 从一个线程池里拿多次连接
         */
        long s = System.currentTimeMillis();
        ThreadConnection e =list.get(0);
        for(int j=0;j<100000;j++){
        	 Connection c = e.getConnection();
       	     //System.out.println(j+"获取连接-> "+c);
       		 e.realse(c);
       }
        long end = System.currentTimeMillis();
        System.out.println("数据库连接池获取10000个连接用时"+(end-s)/1000+"."+(end-s)%1000);
        
        long s1 = System.currentTimeMillis();
        for(int j=0;j<100000;j++){
        	Connection c = H2DBUtil.getConnection();
   	     	//System.out.println(j+"获取连接-> "+c);
       }
        long e1 = System.currentTimeMillis();
        System.out.println("传统获取数据库连接10000个连接用时"+(e1-s1)/1000+"."+(e1-s1)%1000);
      /*  ThreadConnection a = new ThreadConnection(poolName);  
        ThreadConnection b = new ThreadConnection(poolName);  
        ThreadConnection c = new ThreadConnection(poolName);  
        
        Thread t1 = new Thread(a);  
        Thread t2 = new Thread(b);  
        Thread t3 = new Thread(c);  
          
          
        // 设置优先级，先让初始化执行，模拟 线程池 先启动  
        // 这里仅仅表面控制了，因为即使t 线程先启动，也不能保证pool 初始化完成，为了简单模拟，这里先这样写了  
        t1.setPriority(10);  
        t2.setPriority(10);  
        t3.setPriority(10);  
        t1.start();  
        t2.start();  
        t3.start();  
        
          
        System.out.println("线程A-> "+a.getConnection());  
        System.out.println("线程B-> "+b.getConnection());  
        System.out.println("线程C-> "+c.getConnection());  
        */
    }  
  
    // 初始化  
    public static Thread init(String pn) { 
    	final String name = pn;
        Thread t = new Thread(new Runnable() {  
            @Override  
            public void run() {  
                IConnectionPool  pool = initPool(name);  
                while(pool == null || !pool.isActive()){  
                    pool = initPool(name);  
                }  
            }  
        });  
        return t;  
    }  
      
    public static IConnectionPool initPool(String poolName){  
    	IConnectionPool pool  = null;
    	try{
    	   pool = ConnectionPoolManager.getInstance().getPool(poolName);
    	}catch(Exception e){
    		System.out.println(e.toString());
    	}
    	/*try{
    		IConnectionPool h2pool = ConnectionPoolManager.getInstance().getPool("testPool");
    	}catch(Exception e){
    		System.out.println(e.toString());
    	}*/
        return pool;  
    }  
  
}  
