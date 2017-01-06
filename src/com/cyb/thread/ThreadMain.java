package com.cyb.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadMain {
 public static Map<String, FutureTask<String>> tasks = new HashMap<String, FutureTask<String>>();
 public static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);  
		// new ThreadPoolExecutor(6, 7, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()); 
 public static FutureTask<String> futureTask = null;  
 public static void main(String[] args) {
	 /*removeTaskTest();//取消还未开始的任务
	 visitorRunningTask();//访问正在执行的任务
	 */ test4();
	 }
 public static void visitorRunningTask(){
		Random random = new Random();
		List<Future<Map<String, Object>>> results = new ArrayList<Future<Map<String, Object>>>();
		ThreadPoolExecutor execute = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(3);
		for (int i = 1; i <= 10; i++) {
			int number = random.nextInt(20);
			FactorialCalculator task = new FactorialCalculator(number, i + "");
			Future<Map<String, Object>> result = (Future<Map<String, Object>>) execute
					.submit(task);
			results.add(result);
		}
		do {
			try {
				System.out
						.println("*******************************************");
				System.out.printf("Main: Number of Completed Tasks:%d\n",
						execute.getCompletedTaskCount());
				for (int i = 0; i < results.size(); i++) {
					Future<Map<String, Object>> result = results.get(i);
					System.out
							.printf("Main: Task %s: %s\n", i, result.isDone());
				}
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (execute.getCompletedTaskCount() < results.size());
		System.out.println("*******************************************");
		System.out.printf("Main: Results\n");
		for (Future<Map<String, Object>> fus : results) {
			try {
				
				System.out.printf("Main: Task %s: %s\n", fus.get().get("name"),
						fus.get().get("value"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		execute.shutdown();
 }
 public static void removeTaskTest(){
	 for(int i=1;i<=6;i++){
		 SimpleTaskRet task = new SimpleTaskRet("task"+i);
		 addTask(task,task.name);
	 }
	 tasks.get("task1").cancel(true);  
	 tasks.get("task4").cancel(true); 
	 executor.remove((Runnable) tasks.get("task5"));//已经开始的任务则无法移除  
	 executor.remove((Runnable) tasks.get("task3")); 
	 executor.shutdown();
	 System.out.println("返回结果！");
 }
 public void test2(){
	 ThreadPoolExecutor executor = 
			 new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());  
	 SimpleTask task1 = new SimpleTask("task1");
	 SimpleTask task2 = new SimpleTask("task2");
     executor.execute(task1);  
	 executor.execute(task2);
	 executor.remove(task1);
	 executor.shutdown();	 
 }
 public static void test1(){
	 ExecutorService worker = Executors.newSingleThreadExecutor();
	 SimpleTaskRet task1 = new SimpleTaskRet("task1");
	 SimpleTaskRet task2 = new SimpleTaskRet("task2");
	 FutureTask<String> futureTask1 = new FutureTask<String>(task1);  
	 FutureTask<String> futureTask2 = new FutureTask<String>(task2); 
	 futureTask = new FutureTask<String>(task1);
	 worker.submit(futureTask);
	 worker.submit(futureTask1);
	 worker.submit(futureTask2);
	 futureTask1.cancel(true);
	 futureTask.cancel(true);
	 futureTask = new FutureTask<String>(task1);
	 worker.submit(futureTask1);
	 worker.shutdown();
 }
 
 public static void test4(){
	 Map<String, FutureTask<String>> tasks = new HashMap<String, FutureTask<String>>();
	 ExecutorService worker = Executors.newSingleThreadExecutor();
	 SimpleTaskRet task1 = new SimpleTaskRet("task1");
	 SimpleTaskRet task2 = new SimpleTaskRet("task2");
	 FutureTask<String> futureTask1 = new FutureTask<String>(task1);  
	 FutureTask<String> futureTask2 = new FutureTask<String>(task2); 
	 tasks.put("task1", futureTask1);
	 tasks.put("task2", futureTask2);
	 worker.execute(futureTask1);
	 worker.execute(futureTask2);
	 for(int i=1 ;i<=6;i++){
		 if(i==3){
			 tasks.get("task1").cancel(true);//提交两个任务后，取消第1个任务
		 }
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 System.out.println(i+", task2 isdone ? "+tasks.get("task2").isDone());
		 System.out.println(i+",task1 iscancelled ? "+tasks.get("task1").isCancelled());
	 }	 
	 futureTask = new FutureTask<String>(task1);
	 worker.submit(futureTask);//再次提交一个任务
	 worker.shutdown();
 }
 public static String addTask(Callable<String> task,String name) {  
     FutureTask<String> futureTask = new FutureTask<String>(task);  
     executor.execute(futureTask);  
     String key = Long.toHexString(System.nanoTime());  
     tasks.put(name, futureTask);  
     return key;  
 } 
 
}
