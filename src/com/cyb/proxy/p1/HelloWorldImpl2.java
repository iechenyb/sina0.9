package com.cyb.proxy.p1;

public class HelloWorldImpl2 implements HelloWorld2
{ 
	
	@Override
	public void print1() {
		System.out.println("helloworld!");
	}
	@Override
	public void print2(String str,Integer i) {
		System.out.println("hello,"+str);
	}
}
