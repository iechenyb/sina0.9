package com.cyb.jmx;

import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.swing.JDialog;

import com.sun.jdmk.comm.HtmlAdaptorServer;
//通过JMX可以轻松地为应用程序添加管理功能，即可以在尽可能少的改变原有系统的代码基础上实现对原系统的管理。
//JMX(Java Management Extensions) 是来管理网络，设备，应用程序等资源，它描述了一个可扩展的管理体系结构，并且提供了 JMX API 和一些预定义的 java 管理服务。
public class JMXMain {
	public static void  init() throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException{
		//获得MBeanServer实例  
//      MBeanServer mbs = MBeanServerFactory.createMBeanServer();//不能在jconsole中使用  
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();//可在jconsole中使用  
        //创建MBean  
        ControllerMBean controller = new Controller();  
        //将MBean注册到MBeanServer中  
        mbs.registerMBean(controller, new ObjectName("MyappMBean1:name=controller"));  
          
        //创建适配器，用于能够通过浏览器访问MBean  
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();  
        adapter.setPort(9797);  
        mbs.registerMBean(adapter, new ObjectName(  
                "MyappMBean1:name=htmladapter,port=9797"));  
        adapter.start();
          
        //由于是为了演示保持程序处于运行状态，创建一个图形窗口  
        javax.swing.JDialog dialog = new JDialog();  
        dialog.setName("jmx test");  
        dialog.setVisible(true);
	}
	public static void main(String[] args) throws InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException, MalformedObjectNameException {
		//获得MBeanServer实例  
//      MBeanServer mbs = MBeanServerFactory.createMBeanServer();//不能在jconsole中使用  
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();//可在jconsole中使用  
        //创建MBean  
        ControllerMBean controller = new Controller();  
        //将MBean注册到MBeanServer中  
        mbs.registerMBean(controller, new ObjectName("MyappMBean1:name=controller"));  
          
        //创建适配器，用于能够通过浏览器访问MBean  
        HtmlAdaptorServer adapter = new HtmlAdaptorServer();  
        adapter.setPort(9797);  
        mbs.registerMBean(adapter, new ObjectName(  
                "MyappMBean1:name=htmladapter,port=9797"));  
        adapter.start();
          
        //由于是为了演示保持程序处于运行状态，创建一个图形窗口  
        javax.swing.JDialog dialog = new JDialog();  
        dialog.setName("jmx test");  
        dialog.setVisible(true);
        //http://localhost:9797/  访问
	}
}
/**
 * 1.JMX 简介 
  JMX（Java Management Extension）是一个为应用程序植入管理功能的框架，是一套标准的代理跟服务 
2.应用场景 
  2.1用来管理应用程序的配置项，可以在运行期动态改变配置项的值，而不用妨碍程序的运行，这对与许多可靠性要求较高的应用来说非常方便。 
    可以通过jconsole等JMX客户端工具动态改变配置项的值。 
  2.2用来对应用程序的运行状态进行监控，比如对一个大型交易处理程序，我们要监控当前有多少交易在排队中，每笔交易的处理时间是多少，平均每处理一笔交易要花多少时间等等。
  */ 
