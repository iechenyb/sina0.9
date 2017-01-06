package com.cyb.activemq;

/*
 * http://activemq.apache.org/activemq-5133-release.html
 * http://blog.csdn.net/xh16319/article/details/12142249
   http://www.tuicool.com/articles/jABfEff
   http://127.0.0.1:8161/admin/
   Exception in thread "main" javax.jms.IllegalStateException: Cannot synchronously receive a message when a MessageListener is set
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.cyb.date.DateUtil;

public class MessageReceiver implements Runnable{
	public static Logger log = Logger.getLogger(MessageReceiver.class);
    public static void main(String[] args) throws Exception {
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        String url = "tcp://localhost:61616";
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
	   
	    connectionFactory.setUserName("admin");
        connectionFactory.setPassword("amdin");
        Connection connection = connectionFactory.createConnection();
       
        connection.start();
        Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("qutoesQueue");
        MessageConsumer consumer = session.createConsumer(destination);
        Map<String,String> codes = new HashMap<String,String> ();
        codes.put("sh600868", "sh600868");
        //codes.put("sh600108", "sh600108");
        //codes.put("sh600257", "sh600257");
        int xunhuan=0;
        while (true) {
        	xunhuan++;
            MapMessage message = (MapMessage) consumer.receive();        
            if(codes.containsKey(message.getString("code"))){
	            System.out.print("[{SendTime=" +message.getString("sendTime")+",GetTime=" + DateUtil.timeToMilis(new Date()));
	            System.out.println("},Data={" + message.getString("qutoes")+"}]");
            }
            if(xunhuan>5000){
            	xunhuan = 0;
            	session.commit();
            }
        }
        //session.close();
        //connection.close();      
    }
    public static void start(){
    	
    }
	@Override
	public void run() {
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
			Connection connection = connectionFactory.createConnection();
			connection.start();
			final Session session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("my-queue");
			MessageConsumer consumer = session.createConsumer(destination);
			//consumer.setMessageListener(new JMSMSGListener(consumer));//set msg listen 鐩戝惉鍜岀洿鎺ユ帴鏀舵秷鎭簩鑰呭彧鑳介�夋嫨涓�涓�
			while (true) {
			    MapMessage message = (MapMessage) consumer.receive();
			    session.commit();
			    log.info("get message is " + message);
			}
			//session.close();
			//connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}