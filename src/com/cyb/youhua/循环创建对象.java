package com.cyb.youhua;

import com.cyb.collection.User;

public class 循环创建对象 {
	 public static void main(String[] args) {
		    long max = 1*10;
			m1(max);
			m2(max);
			m3(max);
		}
		 public static void m1(long max){
			  long sum = 0;
			   long start = System.currentTimeMillis();
			   //内存中有count份Object对象引用存在，count很大的话，就耗费内存了
			   for(int i=0;i<max;i++){
				   User user = new User();
				   user.setName("name1"+i);
				   user.setPwd("pwd"+1);
				   //System.out.println(user);
			   }
			   long end = System.currentTimeMillis();
			   System.out.println(sum+",total1 time is " +(end-start));
			   //499999999500000000,total time is 7813
		 }
		 public static void m2(long max){
			   long sum = 0;
			   long start = System.currentTimeMillis();
			   User user = new User();
			   for(int i=0;i<max;i++){
				   user.setName("name1"+i);
				   user.setPwd("pwd"+1);
				   //System.out.println(user);
			   }
			   long end = System.currentTimeMillis();
			   System.out.println(sum+",total2 time is " +(end-start));
			   //499999999500000000,total time is 7532
		 }
		 public static void m3(long max){
			   long sum = 0;
			   long start = System.currentTimeMillis();
			   User user = null;
			   /*内存中只有一份Object对象引用，每次new Object()的时候，Object对象引用指向不同的Object罢了，
			   但是内存中只有一份，这样就大大节省了内存空间了*/
			   for(int i=0;i<max;i++){
				   user = new User();
				   user.setName("name1"+i);
				   user.setPwd("pwd"+1);
				   //System.out.println(user);
			   }
			   long end = System.currentTimeMillis();
			   System.out.println(sum+",total2 time is " +(end-start));
			   //499999999500000000,total time is 7746
		 }
}
