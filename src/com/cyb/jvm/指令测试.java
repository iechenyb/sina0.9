package com.cyb.jvm;

import com.cyb.collection.User;

public class 指令测试 {
	static int i0 = 20;
    public static void main(String[] args) {
    	addt();subt();mult();divt();fort();whilet();
    	genCommArr();genObject();
	}
    public static int[] genCommArr(){
    	int[] a=new int[]{1,2,4};
    	return a;
    }
    public static User genObject(){
    	User user = new User("chenyb","ddddd");
    	String name = user.getName();
    	String val = User.staticValue;
    	System.out.println(val);
    	return user;
    }
    public static int whilet(){
    	int i=3;
    	int sum =2;
    	while(i<10){
    		sum=sum+i;
    		i++;
    	}
    	return sum;
    }
    public static int fort(){
    	int sum=2;
    	for(int i=1;i<5;i++){
    		sum = sum+i;
    	}
    	return sum;
    }
    public static int addt(){
    	int i1=1;int i2=2;
    	return i1+i2;
    }
    public static int subt(){
    	int i1=1;int i2=2;
    	return i1-i2;
    }
    
    public static int mult(){
    	int i1=1;int i2=2;
    	return i1*i2;
    }
    public static int divt(){
    	int i1=1;int i2=2;
    	return i1/i2;
    }
    public static void init(){
    	short s1 = -129; //实际存储值为 65536-(-129)
		short s2 = -128;
		short s3 = 127;
		short s4 = 128;
		short s5 = 129;
		short s6 = 250;
		short min1=(short)11;
		short min2 =(short)16;
		short min3 = (short)(min1+min2); 
		int i1=-129;
		int i2=-128;
		int i4=127;
		int i5=128;
		int i6=256;
		int i7=300;
		int i3 = i1+i2;
		long l1=-129;
		long l2=-128;
		long l4=127;
		long l5=128;
		long l6=512;
		long l7=700;
		long l3 = l1+l2;
    }
}
