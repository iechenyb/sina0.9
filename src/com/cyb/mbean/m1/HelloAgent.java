package com.cyb.mbean.m1;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;  
import javax.management.ObjectName;  

import com.sun.jdmk.comm.HtmlAdaptorServer;  
/**
 * 运行后，浏览器可以查看mbean信息
 * @author DHUser
 *
 */
public class HelloAgent {
	 public static void main(String[] args) throws Exception {  
	        MBeanServer server = MBeanServerFactory.createMBeanServer();  
	        
	        ObjectName helloName = new ObjectName("chengang:name=HelloDynamic");  
	        
	        HelloDynamic hello = new HelloDynamic();  
	        
	        server.registerMBean(hello, helloName);  
	        
	        ObjectName adapterName = new ObjectName("HelloAgent:name=htmladapter,port=8082"); 
	        
	        HtmlAdaptorServer adapter = new HtmlAdaptorServer();  
	        
	        server.registerMBean(adapter, adapterName);  
	        
	        adapter.start();  
	        
	        System.out.println("start.....");  
	    }  
}
