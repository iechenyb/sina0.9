package com.cyb.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class SessionsUtil {
	 public static HashMap<String,HttpSession> sessions = new HashMap<String,HttpSession>();

	    public static synchronized void AddSession(HttpSession session) {
	        if (session != null) {
	        	sessions.put(session.getId(), session);
	        }
	    }

	    public static synchronized void DelSession(HttpSession session) {
	        if (session != null) {
	        	sessions.remove(session.getId());
	        }
	    }

	    public static synchronized HttpSession getSession(String session_id) {
	        if (session_id == null)
	        return null;
	        return (HttpSession) sessions.get(session_id);
	    }
}
