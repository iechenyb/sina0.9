package com.cyb.jvm.classloader;

public class StaticProxy_1 implements HelloWorld
{
	HelloWorld interfaces;

	public StaticProxy_1(HelloWorld interfaces)
	{
		this.interfaces = interfaces;
	}

	public void print()
	{
		System.out.println("Before Hello World!");
		interfaces.print();
		System.out.println("After Hello World!");
	}
}