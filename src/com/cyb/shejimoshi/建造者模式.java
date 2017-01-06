package com.cyb.shejimoshi;

import java.util.ArrayList;
import java.util.List;

public class 建造者模式 {
	  private List<Object> list = new ArrayList<Object>();  
      
	    public void produceMailSender(int count){  
	        for(int i=0; i<count; i++){  
	            list.add(i);  
	        }  
	    }  
	      
	    public void produceSmsSender(int count){  
	        for(int i=0; i<count; i++){  
	            list.add(String.valueOf(i));  
	        }  
	    }  
	    public static void main(String[] args) {
	    	建造者模式 obj = new 建造者模式();
	    	obj.produceMailSender(10);
	    	obj.produceSmsSender(10);
		}
}
