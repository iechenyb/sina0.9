package com.cyb.thread;

public class 锁类型 {
	    // 对象锁：形式1
	    public synchronized void objLockMethod1()
	    {
	        System.out.println("in...objLockMethod1");
	        try
	        {
	            Thread.sleep(500);
	        } catch (InterruptedException e)
	        {
	            e.printStackTrace();
	        }
	        System.out.println("out...objLockMethod1");
	    }
	 
	    // 对象锁：形式2
	    public void objLockMethod2()
	    {
	        synchronized (this)
	        {
	            System.out.println("in...objLockMethod2");
	            try
	            {
	                Thread.sleep(500);
	            } catch (InterruptedException e)
	            {
	                e.printStackTrace();
	            }
	            System.out.println("out...objLockMethod2");
	        }
	 
	    }
	 
	    // 类锁：形式1
	    public static synchronized void classLock1()
	    {
	        System.out.println("classLock1------in");
	        try
	        {
	            Thread.sleep(500);
	        } catch (InterruptedException e)
	        {
	            e.printStackTrace();
	        }
	        System.out.println("classLock1------out");
	    }
	 
	    // 类锁：形式2
	    public void classLock2()
	    {
	        synchronized (锁类型.class)
	        {
	            System.out.println("classLock2------in");
	            try
	            {
	                Thread.sleep(500);
	            } catch (InterruptedException e)
	            {
	                e.printStackTrace();
	            }
	            System.out.println("classLock2------out");
	        }
	 
	    }
}
