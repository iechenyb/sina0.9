package com.cyb.activemq;

public class TopicsMain {
	   
	public static void main(String[] args) {
		//这里先启动3个线程来监听FirstTopic的消息，与queue的方式不一样三个线程都能收到同样的消息
	    ReceiveTopic receive1= new ReceiveTopic("thread1");
	    ReceiveTopic receive2= new ReceiveTopic("thread2");
	    ReceiveTopic receive3= new ReceiveTopic("thread3");
	    Thread thread1= new Thread(receive1);
	    Thread thread2= new Thread(receive2);
	    Thread thread3= new Thread(receive3);
	    thread1.start();
	    thread2.start();
	    thread3.start();
	    /*try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
	    SendTopic.startSendTopics();
	}
}
