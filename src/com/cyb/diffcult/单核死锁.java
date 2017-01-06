package com.cyb.diffcult;

import java.util.LinkedList;

import com.cyb.UUIDUtils;

public class 单核死锁 {
	public LinkedList<String> list = new LinkedList<>();
	public synchronized void add(){
		System.out.println("add...");
		synchronized (list) {
			String t = UUIDUtils.getUUID();
			list.add(t);
			System.out.println("###################add"+t);
			notify();
		}
	} 
    public synchronized void pop(){
    	//synchronized (list) {
			if(list.size()<=0){
				try {
					System.out.println("wait....");
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@pop"+list.pop());
		//}		
	}
  public static void main(String[] args) {
	  单核死锁 t = new 单核死锁();
	  System.out.println("start....");
	  new Thread(new AddThread(t)).start();
	  new Thread(new PopThread(t)).start();
  }
}
class AddThread implements Runnable{ 
	  单核死锁 t ;
   AddThread(单核死锁 t){
		  this.t = t;
	  }
	@Override
	public void run() {
		try{
			Thread.currentThread().setName("生产者");
			while(true){
				t.add();
			}
		}catch(Exception e){
			
		}finally{
			System.out.println("消费者over!");
		}
		System.out.println("生产者over!");
	}
}
class PopThread implements Runnable{ 
	  单核死锁 t ;
	  PopThread(单核死锁 t){
		  this.t = t;
	  }
	@Override
	public void run() {
		try{
			Thread.currentThread().setName("消费者");
			while(true){
				t.pop();
			}
		}catch(Exception e){
			
		}finally{
			System.out.println("消费者over!");
		}
		//System.out.println("消费者over!");
	}
}
