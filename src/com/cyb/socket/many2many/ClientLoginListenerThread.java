package com.cyb.socket.many2many;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientLoginListenerThread implements Runnable{
	public  ServerSocket server = null;
	public ClientLoginListenerThread(ServerSocket server){
		this.server = server;
	}
	public void run() {
		while(true){
			try {
				Socket client = server.accept();
				Server.clients_map.put(client.getLocalAddress().toString(), client);
				Server.clients_list.add(client);
				Server.showClient();
				new Thread(new HandlerMessageThread(server, client)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}