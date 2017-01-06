package com.cyb.diffcult;

//pirvate protect  一般的非内部类，是不允许有 private 与protected权限的，但内部类可以
public class 静态类 {
	static int a = 3;
	static int b;

	static void meth(int x) {
		System.out.println("x = " + x);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("-----------------------");
	}

	static {
		meth(1);
		System.out.println("static block initialized1");
		b = a * 4;
	}
	static {
		meth(2);
		a = 1;
		System.out.println("static block initialized2");
		b = a * 4;
	}

	public static void main(String[] args) {
		for(int i=0;i<10;i++){
			System.out.println("执行次数："+(i+1));
			meth(42);//静态语句块仅仅加载一次
		}
	}
}
