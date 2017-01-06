package com.cyb.socket.many2many;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import net.sf.json.JSONObject;

public class ClientSendThread implements Runnable{
	PrintWriter pw = null;
	Socket client = null;
	boolean state = false;
	public ClientSendThread(Socket client) throws IOException{
		this.client = client;
		pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream())));
	}
	public void run() {
		state = true;
		long count = 0;
		try {
			while(state){
				count ++;
				Thread.sleep(1000);
				if(count>100000) break;
				Message mess = new Message();
				mess.setContent("hello world!,"+count);
				mess.setSendTime(new Date().toString());
				mess.setFromSocket(client.toString());
				mess.setToSocket("/192.168.16.211");//client.getLocalAddress().toString();
				pw.println(JSONObject.fromObject(mess).toString());
				pw.flush();
			}
			
		} catch (InterruptedException e) {
			System.out.println("客户端发送消息异常！");
			try {
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			state = false;
		}
	}

}
