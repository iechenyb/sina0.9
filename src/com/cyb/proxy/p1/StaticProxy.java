package com.cyb.proxy.p1;

import java.lang.reflect.Method;

import com.cyb.reflect.MethodUtils;
import com.cyb.reflect.invoke.CallMethod;

public class StaticProxy extends HelloWorldImpl2 implements HelloWorld2
{
	
	public static void main(String[] args) {
		HelloWorld2 h = new HelloWorldImpl2();
		DynamicProxyHandler handler = new DynamicProxyHandler();
		handler.setBusiness(h);//设置代理类
		StaticProxy proxy = new StaticProxy(handler,h.getClass());//使用回调方法
		proxy.print1();
		System.out.println("------------------");
		proxy.print2("chenyb",1);
	}
	
	DynamicProxyHandler interfaces;
	Class<?> clazz ;
	public StaticProxy(DynamicProxyHandler interfaces,Class<?> clazz)
	{
		this.interfaces = interfaces;
		this.clazz = clazz;
	}

	public void print1()
	{
		Method method;
		try {
			method = clazz.getMethod("print1",(Class[]) null);
			interfaces.invoke(interfaces.getBusiness(),method, null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	public void print2(String content,Integer i)
	{
		Method method;
		try {
			Class<?>[] paramType = CallMethod.getParamTypes("print2", clazz);//获取参数类型
			//String[] paramNames = MethodUtils.getMethodParamNames(clazz,"print2");//获取参数名称
			method = clazz.getMethod("print2",paramType);
			Object[] params = new Object[paramType.length];
			params[0] = content;//因为是生成代码，所以，我们只需要将普通的语句写出就行，不需要根据形参名字获取形参数值。
			params[1] = i;
			interfaces.invoke(interfaces.getBusiness(),method, params);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}