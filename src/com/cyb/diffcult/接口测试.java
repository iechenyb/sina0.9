package com.cyb.diffcult;

public class 接口测试  implements 接口{
	  public int  y;
		@Override
		public void jkprint() {
			//x = 5;//默认为final,不能被修改
			y=5;
			System.out.println("print,the value of x in interface is "+x);
			System.out.println("print,the value of y in interface is "+y);//=子类同名属性覆盖接口的同名属性
		}
		public static void main(String[] args) {
			new 接口测试().jkprint();
			//?如何访问接口中的相同属性名的参数值？？？
		}
		@Override
		public void jkprint1() {
			jkprint();
		}
	} 
