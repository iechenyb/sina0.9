package com.cyb.thread;

import com.cyb.collection.User;

public class RunForeverTask  implements Runnable{
	Long total = 0l;
	String name = "";
	StringBuffer age =null;
	
  public static void main(String[] args) {
	  RunForeverTask task = new RunForeverTask();
	  new Thread(task).start();
  }
	@Override
	public void run() {  
		User user = null;
		int i=0;
		int gccount = 0;
		while(true){
			   /*内存中只有一份Object对象引用，每次new Object()的时候，Object对象引用指向不同的Object罢了，
			      但是内存中只有一份，这样就大大节省了内存空间了*/
			   user = new User();
			   user.setName(System.currentTimeMillis()+"");
			   user.setPwd("pwd1");
			   user = null;
			   i++;
			   if(i==100000){
				   i=0;
				   System.gc();
				   gccount++;
				   System.out.println("gc times is "+gccount++);
			   }
		}		
	}
	public void m1(){
		while(true){
			total++;
			if(total%(1000000000)==0){
				System.out.println("累加十亿！");
			}
			if(total>=Long.MAX_VALUE){
				System.out.println("累加置零！");
			}
		}
	}
	
}
