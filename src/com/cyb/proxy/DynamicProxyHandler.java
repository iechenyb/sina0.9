package com.cyb.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA. User: leizhimin Date: 2008-3-20 23:24:10 Company:
 * LavaSoft(http://lavasoft.blog.51cto.com/) 动态代理处理器工具
 */
public class DynamicProxyHandler implements InvocationHandler {
	private Object business; // 被代理对象

	private InterceptorClass interceptor = new InterceptorClass(); // 拦截器

	/**
	 * 动态生成一个代理类对象,并绑定被代理类和代理处理器
	 * 
	 * @param business
	 * @return 代理类对象
	 */
	public Object bind(Object business) {
		this.business = business;
		return Proxy.newProxyInstance(
		// 被代理类的ClassLoader

				business.getClass().getClassLoader(),
				// 要被代理的接口,本方法返回对象会自动声称实现了这些接口

				business.getClass().getInterfaces(),
				// 代理处理器对象

				this);
	}

	/**
	 * 代理要调用的方法,并在方法调用前后调用连接器的方法.
	 * 
	 * @param proxy
	 *            代理类对象
	 * @param method
	 *            被代理的接口方法
	 * @param args
	 *            被代理接口方法的参数
	 * @return 方法调用返回的结果
	 * @throws Throwable
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		interceptor.before();
		System.out.println("method toGenericString:"+method.toGenericString());  
	    System.out.println("method name:"+method.getName());  
	    if(args!=null){
	    	System.out.println("method args:"+(String)args[0]);  
	    }
		result = method.invoke(business, args);
		interceptor.after();
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	public Object getBusiness() {
		return business;
	}

	public void setBusiness(Object business) {
		this.business = business;
	}
}
