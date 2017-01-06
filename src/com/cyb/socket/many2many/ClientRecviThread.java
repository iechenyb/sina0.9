package com.cyb.socket.many2many;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientRecviThread  implements Runnable{
	static Logger log = LoggerFactory.getLogger(ClientRecviThread.class);
	BufferedReader br = null;
	Socket client = null;
	boolean state = false;
	public ClientRecviThread(Socket client) throws IOException{
		this.client = client;
		this.br = br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}
	public void run() {
		state = true;
		log.info("接收消息线程准备完毕！");
		try {
			while(true){
				String msg="";
				if((msg=br.readLine())!=null){
					log.info("new message:"+msg);
				}
			}
		} catch (Exception e) {
			try {
				client.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			state = false;
			log.info("客户端消息接收异常！");
		}
		log.info("接收消息线程结束！");
	}

}
