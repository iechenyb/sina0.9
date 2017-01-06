package com.cyb.activemq;

import org.apache.log4j.Logger;

public class QueueMain {
  public static Logger log = Logger.getLogger(QueueMain.class);
  public static void main(String[] args) {
	  try {
			/*PooledConnectionFactory fac = (PooledConnectionFactory) SpringUtil.getBean("connectionFactory");
			fac.createConnection();*/
			log.info("activemq 连接成功！");
			new Thread(new MessageSender()).start();
			new Thread(new MessageReceiver()).start();
		} catch (Exception e1) {
			log.info(e1.toString());
		}
}
}
