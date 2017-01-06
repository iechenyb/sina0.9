package com.cyb.thread;


public class SimpleJoinTask implements Runnable{
	String name = "";
	public SimpleJoinTask(String name){
		this.name = name;
	}
	@Override
	public void run() {
		boolean flag =true;
		int count =0;
		Thread.currentThread().setName(name);
		try {
			while(flag){
				//long start = System.currentTimeMillis();
				long sum = 0;
				for(int i = 0;i<=100000000;i++){
					sum = i+sum;
					for(int j=0;j<=sum;j++){
						sum=sum*j;
					}
					sum = sum*sum;
				}
				count ++;
				//long end = System.currentTimeMillis();
				//System.out.println("use times is "+(end-start));//100000000 = 1 亿 547毫秒
				if(count>=5){
					flag = false;
				}
			}
//			System.out.println("over!");
			//wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Thread(new SimpleJoinTask("AA")).start();
	}
}
