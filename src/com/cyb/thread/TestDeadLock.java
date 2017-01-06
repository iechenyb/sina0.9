package com.cyb.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestDeadLock implements Runnable{  
	String name = "";
	public TestDeadLock(String name){
	   this.name = name;	
	}
    public String has = "knife"; // 1 knife and 2 fork;
    //static Object knife = new Object(), fork = new Object();  
    static Knife knife = new Knife();
    static Fork fork = new Fork();  
    public static void main(String[] argv){  
       
        SimpleTaskRet task3 = new SimpleTaskRet("返回值线程测试1");
        SimpleTaskRet task4 = new SimpleTaskRet("返回值线程测试2");
        TestDeadLock td3 = new TestDeadLock("小陈#");  
        TestDeadLock td4 = new TestDeadLock("老李#"); 
        Thread t1 = new Thread(td3,"小陈p");  
        Thread t2 = new Thread(td4,"老李p");  
        t1.start();  
        t2.start();  
        
        TestDeadLock td1 = new TestDeadLock("李四-inpool");  
        TestDeadLock td2 = new TestDeadLock("王五-inpool");   
        td1.has = "knife";  
        td2.has = "fork"; 
        
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        fixedThreadPool.submit(td1);
        fixedThreadPool.submit(td2);
        fixedThreadPool.submit(task3);
        fixedThreadPool.submit(task4);//线程池中的线程如果没有没有新的线程加入，则一直驻留在线程池内
        ScheduledThreadPoolExecutor scheduledPool = new ScheduledThreadPoolExecutor(3);
        SimpleTask tasks1 = new SimpleTask("SimpleTask1"); 
        SimpleTask tasks2 = new SimpleTask("SimpleTask2"); 
        SimpleJoinTask taskj = new SimpleJoinTask("SimpleJoinTask");  
        Thread taskJoin  = new Thread(taskj,"taskJoin");
        taskJoin.setPriority(8);
        //scheduledPool.schedule(new Thread(tasks,"scheduled task"),1,TimeUnit.SECONDS);
        scheduledPool.scheduleAtFixedRate(new Thread(tasks1,"scheduled task1"),0,5,TimeUnit.SECONDS);
        scheduledPool.scheduleAtFixedRate(new Thread(tasks2,"scheduled task2"),1,5,TimeUnit.SECONDS);
        scheduledPool.scheduleAtFixedRate(taskJoin,1,5,TimeUnit.SECONDS);
    }  
    public static void kaishi(){
    	TestDeadLock td1 = new TestDeadLock("task1");  
        TestDeadLock td2 = new TestDeadLock("task2");  
        td1.has = "knife";  
        td2.has = "fork";  
        Thread t1 = new Thread(td1);  
        Thread t2 = new Thread(td2);  
        t1.setName("chenyb");
        t2.setName("zhangsan");
        t1.start();  
        t2.start();  
    }
    public void run(){
    	System.out.println(name +" start:has "+ has);  
    	Thread.currentThread().setName(name);  
        if("knife".equals(has)){  //task1 has knife,need fork to eat!
            synchronized (knife){ 
            	/*CollectionFactory.build(10000);
            	List<String> data1 = new ArrayList<String>();
            	for(int i=0;i<1000000;i++){
            		data1.add("hahahahahahhahaha"+i);
            	}*/
            	System.out.println(name+" wait for to get fork...");
                try{  
                    Thread.sleep(500);  
                }catch(Exception e){  
                    e.printStackTrace();  
                }  
                synchronized(fork){  
                    System.out.println("knife");  
                }  
            }  
        }  
        if("fork".equals(has)){//task2 has fork, need knife to eat!  
            synchronized(fork){  
            	/*CollectionFactory.build(10000);
            	List<String> data2 = new ArrayList<String>();
            	for(int i=0;i<1000000;i++){
            		data2.add("hahahahahahhahaha"+i);
            	}*/
            	System.out.println(name+" wait for to get knife...");
                try{  
                    Thread.sleep(500);  
                }catch(Exception e){  
                    e.printStackTrace();  
                }  
                synchronized(knife){  
                    System.out.println("knife");  
                }  
            }  
        }  
        System.out.println(name +" end ,has "+ has);  
    }  
}  
