package com.cyb.listener;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.cyb.DrawUtil;
import com.cyb.activemq.MessageReceiver;
import com.cyb.activemq.MessageSender;
import com.cyb.comconection.ConnectionUtils;
import com.cyb.h2.H2DBUtil;
import com.cyb.h2.H2Manager;
import com.cyb.jmx.JMXMain;
import com.cyb.log.LogUtil;
import com.cyb.mybatis.User;

@WebListener
public class SinaListener implements ServletContextListener {

    public SinaListener() {

    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }

    public void contextInitialized(ServletContextEvent arg0) {
    	H2Manager.start();
    	H2DBUtil.testFileConnection();
		H2DBUtil.testTCPConnection();
		H2DBUtil.testMemConnection();
    	String url = "http://hq.sinajs.cn/list=sh600868";
    	DrawUtil.grabJsonDataFromURL(url);
    	LogUtil.testSLFLog();
    	LogUtil.testTomcatLog();
    	try {
			JMXMain.init();
		} catch (InstanceAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*try{
    	 *   server.xml 配全局  ，context中配置，web中应用
             InitialContext ctx = new InitialContext();
             DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/trade");
             Connection con = ds.getConnection();
             String sql = "select username,password from usr";
             String sql1 = "select 500 from dual";
             ConnectionUtils<User> dbUtils = new ConnectionUtils<User>(con);
             List<User> users = dbUtils.queryForList(sql,User.class);
             System.out.println("jnid get datasource:"+users);
             DataSource ds1 = (DataSource)ctx.lookup("java:comp/env/jdbc/trade1");
             Connection con1 = ds1.getConnection();
             ConnectionUtils<Map<String,Object>> dbUtils1 = new ConnectionUtils<Map<String,Object>>(con1);
             List<Map<String,Object>> maps = dbUtils1.queryForMap(sql1,Map.class);     
             dbUtils.close();
             dbUtils1.close();
             System.out.println("jnid get datasource1:"+maps);
             System.out.println("activemq 连接成功！");
 			new Thread(new MessageSender()).start();
 			new Thread(new MessageReceiver()).start();
	      }catch(Exception e){
	            e.printStackTrace();
	      }*/
    	 
			/*new Thread(new MessageSender()).start();
			new Thread(new MessageReceiver()).start();
			System.out.println("activemq 连接成功！");*/
    }
	
}
