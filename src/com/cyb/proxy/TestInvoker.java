package com.cyb.proxy;

public class TestInvoker {
 public static void main(String[] args) {
	print("chenyb");
	Person person = new ZhangSan();
	person.run();
}
 public static void print(String str){
	 System.out.println(str);
 }
}
