package com.cyb.compile;

public class HelloWorldImpl2 implements HelloWorld,HelloWorld2
{ @Override
    public void print()
    {
        System.out.println("Hello World");
    }
    @Override
	public void print2() {
		System.out.println("Hello World2");
	}
}
