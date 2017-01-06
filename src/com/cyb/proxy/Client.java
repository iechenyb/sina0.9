package com.cyb.proxy;

public class Client {
	public static void main(String args[]) {
		DynamicProxyHandler handler = new DynamicProxyHandler();
		//是否代理的类必须都有接口?
		BusinessInterface business = new BusinessClass();
		BusinessInterface businessProxy = (BusinessInterface) handler.bind(business);
		businessProxy.doSomething();
		businessProxy.doSomething();
		System.out.println("*******************");
		Person person = new ZhangSan();
		Person personProxy = (Person) handler.bind(person);
		personProxy.run();
		System.out.println("*******************");
		/*Chenyb zs = new Chenyb();
		Chenyb zsProxy = (Chenyb) handler.bind(zs);
		zsProxy.run();*/
	}
}
