package com.cyb.mbean;


public class Echo  implements EchoMBean {
	    public void print(String yourName) {  
	        System.out.println("Hi " + yourName + "!");  
	   }
		
}
