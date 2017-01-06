package com.cyb.activemq;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ReceiveTopic implements Runnable {
	  private String threadName;
      static String url = "tcp://localhost:61616";
	  
      static Map data = new LinkedHashMap();
      ReceiveTopic(String threadName) {

           this.threadName = threadName;

      }

 

      public void run() {

           // ConnectionFactory锛氳繛鎺ュ伐鍘傦紝JMS鐢ㄥ畠鍒涘缓杩炴帴

           ConnectionFactory connectionFactory;

           // Connection锛欽MS瀹㈡埛绔埌JMS Provider鐨勮繛鎺�

           Connection connection = null;

           // Session锛氫竴涓彂閫佹垨鎺ユ敹娑堟伅鐨勭嚎绋�

           Session session;

           // Destination锛氭秷鎭殑鐩殑鍦�;娑堟伅鍙戦�佺粰璋�.

           Destination destination;

           //娑堣垂鑰咃紝娑堟伅鎺ユ敹鑰�

           MessageConsumer consumer;

           connectionFactory = new ActiveMQConnectionFactory(

                      ActiveMQConnection. DEFAULT_USER,

                      ActiveMQConnection. DEFAULT_PASSWORD,url);

           try {

                 //鏋勯�犱粠宸ュ巶寰楀埌杩炴帴瀵硅薄

                 connection = connectionFactory.createConnection();

                 //鍚姩

                 connection.start();

                 //鑾峰彇鎿嶄綔杩炴帴,榛樿鑷姩鍚戞湇鍔″櫒鍙戦�佹帴鏀舵垚鍔熺殑鍝嶅簲

                 session = connection.createSession( false, Session. AUTO_ACKNOWLEDGE);

                 //鑾峰彇session娉ㄦ剰鍙傛暟鍊糉irstTopic鏄竴涓湇鍔″櫒鐨則opic

                 destination = session.createTopic("FirstTopic");

                 consumer = session.createConsumer(destination);

                 while ( true) {

                      //璁剧疆鎺ユ敹鑰呮帴鏀舵秷鎭殑鏃堕棿锛屼负浜嗕究浜庢祴璇曪紝杩欓噷璁惧畾涓�1s

                      TextMessage message = (TextMessage) consumer

                                  .receive();

                      if ( null != message) {
                    	    data.put(message.getText(), threadName);
                            System. out.println("绾跨▼"+threadName+"鏀跺埌娑堟伅:" + message.getText());
                            //session.commit();

                      } else {

                            continue;

                      }
                     

                 }
                 //System.out.println(data);

           } catch (Exception e) {

                 e.printStackTrace();

           } finally {

                 try {

                      if ( null != connection)

                            connection.close();

                 } catch (Throwable ignore) {

                 }

           }
      }
}
