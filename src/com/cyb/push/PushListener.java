package com.cyb.push;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class PushListener implements DataListener<Object>{
  public static Logger log = LoggerFactory.getLogger(PushListener.class);
  SocketIOServer server;
  public PushListener(SocketIOServer server){
  	this.server = server;
  }
  public void onData(SocketIOClient client, Object action, AckRequest req)  {
	  try{
		log.debug(" "+action+",client:"+client);

	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}