package com.cyb.diffcult;
/**
 * 静态语句块和静态方法的执行是严格有序的！！！
 * @author DHUser
 *
 */
public class 静态语句块父类 { 
  public static String name="chenyb";
  public 静态语句块父类(String name){
	 this.name=name;
	 System.out.println(this.name+"#f1");
  }
   static{
	   System.out.println(name+"#f2");
	   name="cyb";
   }
   static {
	   System.out.println(name+"#f3");
   }
   public static 静态语句块父类 obj= new 静态语句块父类("静态语句块");
   public static void main(String[] args) {
	 //new 静态语句块父类();
   }
}
