package com.cyb.youhua;

import java.util.List;

import com.cyb.collection.CollectionFactory;

public class 数组遍历 {

	 public static void main(String[] args) {
		 CollectionFactory.build(1000000);
		 System.out.println("build over!");
			m1();
			m2();
		}
		 public static void m1(){
			  long sum = 0;
			  List<Integer> list =  CollectionFactory.getList();
			  System.out.println(list.size());
			   long start = System.currentTimeMillis();
			   for(int i=0;i<list.size();i++){
				   sum= sum+list.get(i);
			   }
			   long end = System.currentTimeMillis();
			   System.out.println(sum+",total1 time is " +(end-start));
			   //499999999500000000,total time is 13
		 }
		 public static void m2(){
			   long sum = 0;
			   long start = System.currentTimeMillis();
			   List<Integer> list =  CollectionFactory.getList();
			   for(int i=0,max =list.size();i<max;i++){
				   sum= sum+list.get(i);
			   }
			   long end = System.currentTimeMillis();
			   System.out.println(sum+",total2 time is " +(end-start));
			   //499999999500000000,total time is 8
		 }
}
