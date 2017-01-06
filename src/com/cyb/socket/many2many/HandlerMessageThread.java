package com.cyb.socket.many2many;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import net.sf.json.JSONObject;

public class HandlerMessageThread implements Runnable {
	public ServerSocket server = null;
	public Socket client = null;
	BufferedReader br = null;

	public HandlerMessageThread(ServerSocket server, Socket client) throws IOException {
		this.server = server;
		this.client = client;
		br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}

	public void run() {
			String tmpMsg = "";	
			PrintWriter pw = null;
			try {
			
			System.out.println("服务端为" + client + "开启了消息处理线程！");
			while (true) {
				Socket to = null;
				// 用于发送返回信息,可以不需要装饰这么多io流使用缓冲流时发送数据要注意调用.flush()方法
				String msg = br.readLine();
				if(!"".equals(msg)&&msg!=null){
					JSONObject msgObject = JSONObject.fromObject(msg);
					tmpMsg = msgObject.toString();
					to = Server.clients_map.get(msgObject.get("toSocket"));//获取接收信息目标对象
					pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(to.getOutputStream())), true);
					System.out.println(" 服务器正在转发由:"+msgObject.get("fromSocket")+"发送给 "+msgObject.get("toSocket")+"的消息：" + msgObject.get("content"));
					pw.println(new Date().toString()+msgObject.get("content"));
					pw.flush();
				}
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("消息 "+tmpMsg+" 处理异常，请检查通信通道是否正常！");
			}
	}

}
