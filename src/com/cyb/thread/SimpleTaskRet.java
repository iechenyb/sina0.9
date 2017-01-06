package com.cyb.thread;

import java.util.concurrent.Callable;

public class SimpleTaskRet implements Callable<String>{
	public String name = "";
	public SimpleTaskRet(String name){
		this.name = name;
	}
	@Override
	public String call() {
		try {
			Thread.currentThread().setName(name);  
			Thread.sleep(10);
		} catch (Exception e) {
			System.out.println("任务["+name+"]已取消或者异常终止！"+e.getMessage());
			return "fail";
		}
		return "success";
	}
	public static void main(String[] args) {
		SimpleTaskRet task = new SimpleTaskRet("callname");
	}
}
