package com.cyb.diffcult;

public class 抽象类测试 extends 抽象类{
 public String y = "56";
 public static void main(String[] args) {
	 抽象类测试 obj = new 抽象类测试();
	System.out.println("x="+obj.x+",y="+obj.y);
	obj.print1();
	obj.print2();//实现方法，
 }
   public void print1(){
	   System.out.println("print1方法被重写！");
   } 
	@Override
	public void print2() {
		print();
	}
	
	@Override
	public void jkprint() {
		System.out.println("jkprint");
	}
	@Override
	public void jkprint1() {
		System.out.println("jkprint1");
	}
}
