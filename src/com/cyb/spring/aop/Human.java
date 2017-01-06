package com.cyb.spring.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Human implements Sleepable {  
	Log log = LogFactory.getLog(Human.class); 
    public void sleep() {  
        log.info("失眠了！哎！一只羊。。。Z 。zzz。梦中自有颜如玉");  
    }  
    public String sleep(String str) {  
       log.info("@失眠了！哎！一只羊。。。Z 。zzz。梦中自有颜如玉@");  
        if(str.equals("chenyb")){
        	int i=1/0;
        	System.out.println(i);
        	//throw new Exception(" my exception!");
        }
        return str;
    }
	@Override
	public String sleep1(String str) {
		 log.info("*失眠了！哎！一只羊。。。Z 。zzz。梦中自有颜如玉*");  
	        if(str.equals("chenyb")){
	        	int i=1/0;
	        	System.out.println(i);
	        	//throw new Exception(" my exception!");
	        }
	        return str;
	}  
  
}  