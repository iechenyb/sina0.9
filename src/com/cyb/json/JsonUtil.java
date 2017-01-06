package com.cyb.json;
import java.util.Date;

import net.sf.json.JSONObject;

import com.cyb.socket.one2one.Message;

public class JsonUtil {
  public static void main(String[] args) {
	  Message mess = new Message();
	  mess.setContent("hello world!");
	  mess.setFromSocket("sender");
	  mess.setToSocket("四十多分");
	  mess.setSendTime(new Date().toString()); 
	  //bean to jsonObject
	  JSONObject  jsonData = JSONObject.fromObject(mess);
	  System.out.println(jsonData.toString());
	  
	  //jsonData to bean
      Message msg =  (Message) JSONObject.toBean(jsonData, Message.class) ;
      System.out.println(msg);
      
      //jsonDataString to jsonData
      String str = jsonData.toString();
      JSONObject a = JSONObject.fromObject(str);  
      System.out.println(a.get("content"));
      
     }
  public void test(){
	  Message mess = new Message();
	  mess.setContent("hello world!");
	  mess.setFromSocket("sender");
	  mess.setToSocket("reciver");
	  mess.setSendTime(new Date().toString()); 
	  //bean to jsonObject
	  JSONObject  jsonData = JSONObject.fromObject(mess);
	  System.out.println(jsonData.toString());
	  
	  //jsonData to bean
      Message msg =  (Message) JSONObject.toBean(jsonData, Message.class) ;
      System.out.println(msg);
      
      //jsonDataString to jsonData
      String str = jsonData.toString();
      JSONObject a = JSONObject.fromObject(str);  
      System.out.println(a.get("content"));
  }
}
