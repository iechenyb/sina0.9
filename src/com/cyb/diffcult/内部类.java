package com.cyb.diffcult;

public class 内部类 {
 public String name ="chenyb";
 public static String name1 ="staticchenyb";
 public static Runnable task = new Runnable() {
	@Override
	public void run() {
		System.out.println("task run...");
	}
 };
 public Inner1 obj1= new Inner1();//静态类对象创建
 public Inner obj= new Inner();//静态类对象创建
 public static Inner1 sobj1= new Inner1();//静态类对象创建
 public static Inner sobj= new 内部类().new Inner();//非静态类对象创建
  class Inner implements 接口{
	  class Inner2{
		  class Inner3{
			  void print(){
				  System.out.println("inner3,"+name);
			  }
		  }
	  }
	  void print(){
		  System.out.println("inner,"+name);
	  }
	@Override
	public void jkprint() {
		
	}
	@Override
	public void jkprint1() {
		
	}
  }
  public static class Inner1{//可以被jvm里所有其他的类访问
	  static{
		 System.out.println("static{}init,"+name1); 
	  }
	  public Inner1(){
		  System.out.println("static class init,"+name1);
	  }
	  void print(){
		  System.out.println("inner1,"+name1);
	  }
  }
  public void foo(){ 
	  new Inner();
	  new Inner1();
  }
  public static void bar(){
	  //this.xx;//err
	  //new Inner();//err
	  new 内部类().new Inner();
	  new Inner1();
	  //new 内部类().new Inner1();//err
	  //内部类().new Inner1();//err
  }
  public static void main(String[] args) {
	  new 内部类().new Inner().print();
	  new 内部类().new Inner().new Inner2().new Inner3().print();
	  new Inner1().print();
	  System.out.println("$$$$$$$$$$$$$$$$");
	  new 内部类().obj.print();
	  new 内部类().obj1.print();
	  sobj.print();
	  sobj1.print();
  }
}
