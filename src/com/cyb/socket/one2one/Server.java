package com.cyb.socket.one2one;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *************************************************************** 
 * 项目名称：JavaThread
 * 程序名称：JabberServer
 * 日期：2012-8-23 上午11:36:12
 * 作者：
 * 模块：
 * 描述：
 * 备注：
 * ------------------------------------------------------------
 * 修改历史
 * 序号  				日期        		修改人       修改原因
 * 
 * 修改备注：
 * @version 
 ***************************************************************
 */
public class Server {

	public static int PORT = 9193;
	public static void main(String[] agrs) {
		ServerSocket s = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			//设定服务端的端口号
			s = new ServerSocket(PORT);
			System.out.println("ServerSocket Start:"+s);
			//等待请求,此方法会一直阻塞,直到获得请求才往下走
			socket = s.accept();
			System.out.println("Connection accept socket:"+socket);
			//用于接收客户端发来的请求
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//用于发送返回信息,可以不需要装饰这么多io流使用缓冲流时发送数据要注意调用.flush()方法
			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			while(true){
				String str = br.readLine();
				if(str.equals("END")){
					break;
				}
				System.out.println("from Client Socket Message:"+str);
				//Thread.sleep(1000);
				pw.println("Message Received");
				pw.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			/*System.out.println("Close.....");
			try {
				br.close();
				pw.close();
				socket.close();
				s.close();
			} catch (Exception e2) {
				
			}*/
		}
	}
}
