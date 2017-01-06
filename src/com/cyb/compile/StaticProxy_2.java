package com.cyb.compile;

public class StaticProxy_2 implements HelloWorld
{
	HelloWorld interfaces;

	public StaticProxy_2(HelloWorld interfaces)
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