package com.cyb.jvm;

import com.cyb.collection.User;

public class 常量栈 {
    
	public static void main(String[] args) {
		m1();m2();Object obj;
		User user = new User("chenyb","123");
		user.setId(56);	m5("haha",90);
	}
	public static void m5(String x,int y){
		System.out.println(x+y);
	}
    @SuppressWarnings("unused")
	public static void m1(){
    	int i=1;
		int i1=2;
		int i2=3;
		int i3=4;
		int i4=5;
		int i5=6;
		int i6=7;
		int i7=8;
		int i8=9;
		int i9=10;
		int i10=11;
		int i11=12;
		int i12=13;
		int i13=22;
		int i14=33;
		int i15=44;
		int i16=100;
		int i17=i1+i15;
		int sum = ((i+i1)*58+i7/i3-19)%100+1;
    }
    @SuppressWarnings("unused")
	public static void m2(){
    	int sum = ((1+2)*58+36/6-19)%100+1;
    }
}
