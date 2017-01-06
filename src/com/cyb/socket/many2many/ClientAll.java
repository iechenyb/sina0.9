package com.cyb.socket.many2many;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyb.log.LogUtil;

import net.sf.json.JSONObject;

class ClientRecviThread1  implements Runnable{
	static Logger log = LoggerFactory.getLogger(ClientRecviThread1.class);
	BufferedReader br = null;
	Socket client = null;
	boolean state = false;
	public ClientRecviThread1(Socket client) throws IOException{
		this.client = client;
		this.br = br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}
	public void run() {
		state = true;
		log.info("������Ϣ�߳�׼����ϣ�");
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
				e1.printStackTrace();
			}
			state = false;
			log.info("�ͻ�����Ϣ�����쳣��");
		}
		log.info("������Ϣ�߳̽�����");
	}

}
class ClientSendThread1 implements Runnable{
	static Logger log = LoggerFactory.getLogger(ClientSendThread1.class);
	PrintWriter pw = null;
	Socket client = null;
	boolean state = false;
	public ClientSendThread1(Socket client) throws IOException{
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
				if(count>10) break;
				Message mess = new Message();
				mess.setContent("hello world!,"+count);
				mess.setSendTime(new Date().toString());
				mess.setFromSocket(client.toString());
				mess.setToSocket("/"+ClientAll.toClient);//client.getLocalAddress().toString()
				pw.println(JSONObject.fromObject(mess).toString());
				pw.flush();
			}
			
		} catch (InterruptedException e) {
			log.info("�ͻ��˷�����Ϣ�쳣��");
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
public class ClientAll {
	static Logger log = LoggerFactory.getLogger(ClientAll.class);
	public static String serverIp = "192.168.16.211";
	public static String toClient = "192.168.16.57";
	public static void main(String[] args) {
		Socket socket = null;
		try {
			//�ͻ���socketָ���������ĵ�ַ�Ͷ˿ں�
			socket = new Socket(serverIp, 9193);
			log.info("����������ӳɹ���");
			new Thread(new ClientSendThread1(socket)).start();
			new Thread(new ClientRecviThread1(socket)).start();
			//ͬ������ԭ��һ��
		} catch (Exception e) {
			log.info("�ͻ��˳����쳣���������Ա��ϵ��");
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
	}

}
