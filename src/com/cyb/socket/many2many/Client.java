package com.cyb.socket.many2many;
import java.io.IOException;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyb.log.LogUtil;


public class Client {
	static Logger log = LoggerFactory.getLogger(Client.class);
	public static boolean state = false;
	public static void main(String[] args) {
		Socket socket = null;
		try {
			state = true;
			//�ͻ���socketָ���������ĵ�ַ�Ͷ˿ں�
			socket = new Socket("192.168.16.211", 9193);
			log.info("����������ӳɹ���");
			new Thread(new ClientSendThread(socket)).start();
			new Thread(new ClientRecviThread(socket)).start();
			//ͬ������ԭ��һ��
		} catch (Exception e) {
			log.info("�ͻ��˳����쳣���������Ա��ϵ��");
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally{
			state = false;	
		}
	}

}
