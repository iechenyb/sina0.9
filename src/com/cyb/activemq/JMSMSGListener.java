package com.cyb.activemq;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;

  
public class JMSMSGListener implements MessageListener {  
	MessageConsumer consumer;
	public JMSMSGListener(MessageConsumer consumer){
		this.consumer = consumer;
	}
	public static Logger log = Logger.getLogger(JMSMSGListener.class);
    public void onMessage(Message message) {  
        try {  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
 }  
}