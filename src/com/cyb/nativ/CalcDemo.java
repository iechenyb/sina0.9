package com.cyb.nativ;

import com.cyb.date.DateUtil;

public class CalcDemo {
	
	 static {
		 System.out.println(System.getProperty("java.library.path"));
		 System.loadLibrary("CalcClass");
		 System.loadLibrary("CalcDemo");
	 }	 
	 public native  int MySub( int x,int y) ;
	 public static void main(String[] args) {
		/*System.out.println(System.getProperty("java.library.path"));
		int ret = new CalcDemo().MySub(1, 2);
		System.out.println(ret);
		System.out.println("xxx");*/
		 DateUtil.format("xxx", "xxxx");
	}
}
