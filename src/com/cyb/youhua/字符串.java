package com.cyb.youhua;

public class 字符串 {
 public static void main(String[] args) {
	m1(10000);
	m2(10000);
}
 public static void m1(long idx){
	   String sum = "";
	   long start = System.currentTimeMillis();
	   for(int i=0;i<idx;i++){
		  sum = sum +String.valueOf(i);
	   }
	   long end = System.currentTimeMillis();
	   System.out.println(sum+",total time is " +(end-start));
	   //499999999500000000,total time is 176
 }
 public static void m2(long idx){
	   StringBuffer sum = new StringBuffer("");
	   long start = System.currentTimeMillis();
	   for(int i=0;i<idx;i++){
		   sum.append(i);
	   }
	   long end = System.currentTimeMillis();
	   System.out.println(sum+",total time is " +(end-start));
	   //499999999500000000,total time is 1
 }
}
