package com.cyb.shejimoshi;

/**

 * 单例之饿汉模式
	该模式是类加载的的时候创建好一个静态对象，不管使用不使用，对象已经创建好了，并且是线程安全的。
 */
public class 单例模式1 {
	  private static 单例模式1 singleTon = new 单例模式1();
	  public static int count1;
	  public static int count2 = 0;
	  private 单例模式1() {
	    count1++;
	    count2++;
	  }
	  public static 单例模式1 getInstance() {
	    return singleTon;
	  }
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		单例模式1 singleTon = 单例模式1.getInstance();
	    System.out.println("count1=" + singleTon.count1);
	    System.out.println("count2=" + singleTon.count2);
	    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
	    System.out.println("count1=" + 单例模式1.count1);
	    System.out.println("count2=" + 单例模式1.count2);
	    /**
	     *  count1=1
			count2=0
			$$$$$$$$$$$$$$$$$$$$$$$
			count1=1
			count2=0
	     */
	  }
	  /**
	   * 分析:
		1:SingleTon singleTon = SingleTon.getInstance();调用了类的SingleTon调用了类的静态方法，触发类的初始化 
		2:类加载的时候在准备过程中为类的静态变量分配内存并初始化默认值 singleton=null count1=0,count2=0 
		3:类初始化化，为类的静态变量赋值和执行静态代码快。singleton赋值为new SingleTon()调用类的构造方法 
		4:调用类的构造方法后count=1;count2=1 
		5:继续为count1与count2赋值,此时count1没有赋值操作,所有count1为1,但是count2执行赋值操作就变为0
	   */
}
