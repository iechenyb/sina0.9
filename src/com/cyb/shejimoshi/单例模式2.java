package com.cyb.shejimoshi;

/**

 * 单例之懒汉
该模式很懒，不用的时候不会创建对象，只有用的时候才会创建，但在多线程的情况下，
需要让方法互斥，才能保证只会创建一个实例，如果不加synchronized，
当多个线程同时获取实例时，就有可能创建多个实例。
 */
public class 单例模式2 {
	  private static 单例模式2 singleTon = null;
	  public static int count1;
	  public static int count2 = 0;
	  private 单例模式2() {
	    count1++;
	    count2++;
	  }
	  public static synchronized 单例模式2 getInstance() {
		  if(singleTon==null){
			  singleTon = new 单例模式2();
		   }
		   return singleTon;
	  }
	  
	  /**
	   * 第一个instance == null是判断当前是否为空，如果为空才对创建实例的代码块上互斥锁，
	   * 第二个instance == null是两个线程互斥了，
	   * 第一个线程创建完对象，第二个线程直接返回。
	   * 这种方式是在懒汉模式上的一个进化，避免了无用的互斥开销。
	   * @return
	   */
	  public static 单例模式2 getInstance2() {
	      if(singleTon ==null) {
	        synchronized (单例模式2.class) {
	           if (singleTon ==null) {
	        	   singleTon =new 单例模式2();
	           }
	        }
	      }
	      return singleTon;

	   }
	  
	  @SuppressWarnings("static-access")
	public static void main(String[] args) {
		单例模式2 singleTon = 单例模式2.getInstance();
	    System.out.println("count1=" + singleTon.count1);
	    System.out.println("count2=" + singleTon.count2);
	    System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
	    System.out.println("count1=" + 单例模式2.count1);
	    System.out.println("count2=" + 单例模式2.count2);
	    /**
	     *  count1=1
			count2=1
			$$$$$$$$$$$$$$$$$$$$$$$
			count1=1
			count2=1
	     */
	  }
}
/**
单例模式由于构造器是私有化的，所以单例的类不能被继承。
优缺点分析，饿汉模式，类加载的时候就会创建单例对象，保证了多线程情况下的单例，
同时有可能这个单例对象永远也不会被用到；懒汉模式，只有用到单例的时候才会创建对象，不用就不会创建，多线程情况下互斥开销较大。*/