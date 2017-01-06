package com.cyb.shejimoshi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

class Advice {
	/** 前置增强 */
	public void beforeMethod() {
		System.out.println("前置增强");
	}
	/** 后置增强 */
	public void afterMethod() {
		System.out.println("后置增强");
	}
	/** 异常增强 */
	public void catchMethod() {
		System.out.println("异常增强");
	}
	/** 最后执行增强 */
	public void finallyMethod() {
		System.out.println("最后增强");
	}
}
/*class MyObject {
	public void service() {
		System.out.println("hahaha");
	}
}
class ProxyObject {
	private MyObject obj;
	public ProxyObject(MyObject obj) {
		this.obj = obj;
	}
	public void service() {
		// do something
		this.obj.service();
	}
}*/
class ProxyFactory {
	   /** 代理类的增强对象 */
	   private Advice advice;
	   /** 代理的目标对象 */
	   private Object target;
	   public ProxyFactory() {}
	   public ProxyFactory(Advice advice, Object target) {
	      this.advice = advice;
	      this.target = target;
	   }
	   /**

	    * 得到代理对象

	    * @return

	    */

	   public Object getProxy() {
	      Object proxy = Proxy.newProxyInstance(
	           target.getClass().getClassLoader(),target.getClass().getInterfaces(),
	           new InvocationHandler() {
	              /**
	               * 代理对象需要执行的方法
	               */
	              public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) {
	                 Object result = null;
	                 advice.beforeMethod();
	                 try {
	                    result = method.invoke(target, args);
	                    advice.afterMethod();
	                 } catch (Exception e) {
	                    advice.catchMethod();
	                 } finally {
	                    advice.finallyMethod();
	                 }
	                 return result;
	              }
	           });
	      return proxy;
	   }
	   public void setAdvice(Advice advice) {
	      this.advice = advice;
	   }
	   public void setTarget(Object target) {
	      this.target = target;
	   }
	}
interface ITest {

  void test();

}
class Test implements ITest {
   public void test() {
      System.out.println("目标对象");
   }
}
public class 动态代理模式 {
	public static void main(String[] args) {
	      ProxyFactory pf = new ProxyFactory(new Advice(), new Test());
	      ITest test = (ITest) pf.getProxy();
	      test.test();
	 }
}
