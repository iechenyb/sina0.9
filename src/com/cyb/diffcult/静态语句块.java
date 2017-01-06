package com.cyb.diffcult;
/**
 * 静态语句块和静态方法的执行是严格有序的！！！
 * 接口中的无静态语句块，域定义对象不会执行！
 * @author DHUser
 *
 */
public class 静态语句块 extends 静态语句块父类 implements 接口 { 
  public static String name="chenyb";
  public 静态语句块(String name){
	 super(name);
	 this.name=name;
	 System.out.println(name+"#1"); 
	
  }
   static{
	   System.out.println(name+"#2");
	   name="cyb";
   }
   static {
	   System.out.println(name+"#3");
   }
   public static 静态语句块 obj= new 静态语句块("静态语句块 field");
   public static void main(String[] args) {
	 //new 静态语句块();
   }
	@Override
	public void jkprint() {}
	@Override
	public void jkprint1() {}
}
