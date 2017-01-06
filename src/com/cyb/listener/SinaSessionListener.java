package com.cyb.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@WebListener
public class SinaSessionListener implements HttpSessionListener {
	static Log log =LogFactory.getLog(SinaSessionListener.class);
  
    public SinaSessionListener() {
      
    }

	
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    	   SessionsUtil.AddSession(httpSessionEvent.getSession());
    	   log.info("new session,id is "+httpSessionEvent.getSession());
    }

	
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    	 HttpSession session = httpSessionEvent.getSession();
         SessionsUtil.DelSession(session);
         log.info("remove session,id is "+session.getId());
    }
	
}
