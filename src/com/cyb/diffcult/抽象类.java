package com.cyb.diffcult;

public abstract class 抽象类 implements 接口 {
  public String x="12";
  public String y="34";
  public void print(){
	  System.out.println("kal");
  }
  public void print1(){};//空实现
  
  public abstract void print2();//抽象方法，没有方法体（可以没有抽象方法，但是如果有，则其派生类不必是抽象类）
 
  public static void main(String[] args) {
	 System.out.println("hahah");	 
 }
}
