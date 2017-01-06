package com.cyb.spring;



/**
 * 模拟spring的依赖注入
 * @author DHUser
 *
 */
public class IOCTest {
	public MyApplicationContext ac = null;
	public void init(){
		//从resource加载
		ac = new MyApplicationContext("MyApplicationContext.xml");
	}
    public void testSpringIOC() {
    	//实现类赋值给接口
    	Person person = (Person)ac.getBean("chinese");
    	person.save();
    	person.useAxe();
    	//实现类赋值给实现类
    	Chinese per = (Chinese)ac.getBean("chinese");
    	per.save();
    	per.useAxe();
    	
    	System.out.println("获取普通属性："+per.getName());
    }
    public static void main(String[] args) {
    	IOCTest ioc = new IOCTest();
    	ioc.init();
    	ioc.testSpringIOC();
	}
}
