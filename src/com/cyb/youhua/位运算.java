package com.cyb.youhua;

public class 位运算 {
   public static void main(String[] args) {
	m1(1000000000);
	m2(1000000000);
   }
   public static void m1(long idx){
	   long sum = 0;
	   long start = System.currentTimeMillis();
	   for(int i=0;i<idx;i++){
		   sum = sum+i;
	   }
	   long end = System.currentTimeMillis();
	   System.out.println(sum+",total time is " +(end-start));
	   //499999999500000000,total time is 2569
   }
   public static void m2(long idx){
	   long sum = 0;
	   long start = System.currentTimeMillis();
	   sum = (idx-1)*idx>>1;
	   long end = System.currentTimeMillis();
	   System.out.println(sum+",total time is " +(end-start));
	   //499999999500000000,total time is 0
   }
}
