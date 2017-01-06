package com.cyb.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.cyb.Contanst;
import com.cyb.computer.ComputerUtil;

public class PushUtil {
	public static Logger log = LoggerFactory.getLogger(PushUtil.class);
	public  static List<SocketIOClient> clients = new ArrayList<SocketIOClient>();
	public static SocketIOServer server;
	public static boolean isStarted = false;
	public static void startPushServer() {
		try {
			Configuration config = new Configuration();
			config.setHostname(ComputerUtil.getRealIP());//
			config.setPort(Contanst.port);
			server = new SocketIOServer(config);
			PushListener listener = new PushListener(server);
			server.addEventListener("getmsg", Object.class, listener);
			server.addConnectListener(new ConnectListener() {
				public void onConnect(SocketIOClient client) {
					log.info("new sessionid=" + client.getSessionId());
					clients.add(client);
				}
			});
			server.start();
			log.info("puser server started!");
			Timer timer = new Timer();
		    timer.schedule(new TimerTask() {
		      @Override
		      public void run() {
		        Random random = new Random();
		        for(SocketIOClient client : clients) {
		          client.sendEvent("pushpoint", new Point(random.nextInt(100), random.nextInt(100)));//每隔�?秒推送一�?
		        }
		      }
		    }, 10, 100);
		    
		    Object object = new Object();
		    synchronized (object) {
		      object.wait();
		    }
		} catch (Exception e) {
			log.info("push server start ocurr exception!\n"+e.toString());
		}
	}
	public static void main(String[] args) {
		startPushServer();
	}
}
