package com.cyb.jvm.classloader;

public class StaticProxy implements HelloWorld
{
	HelloWorldImpl interfaces;

	public StaticProxy(HelloWorldImpl interfaces)
	{
		this.interfaces = interfaces;
	}

	public void print()
	{
		System.out.println("Before Hello World!");
		interfaces.print();
		System.out.println("After Hello World!");
	}
	public void print2()
	{
		System.out.println("Before Hello World!");
		interfaces.print2();
		System.out.println("After Hello World!");
	}
}