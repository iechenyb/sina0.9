package com.cyb.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyb.listener.SessionsUtil;

@WebServlet("/SessionEnter")
public class SessionEnter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SessionEnter() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		if("login".equals(cmd)){
			request.getSession(true).setAttribute("name", "chenyb");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher .forward(request, response);
		}else if("out".equals(cmd)){
			request.getSession().removeAttribute("name");
			response.sendRedirect("index.jsp");
		}else if("show".equals(cmd)){
			PrintWriter out = response.getWriter();  
			out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=========================<br/>");  
			Set<String> keys = SessionsUtil.sessions.keySet();
			Iterator<String> iter = keys.iterator();
			String id = request.getParameter("id");
			while(iter.hasNext()){
				HttpSession session = SessionsUtil.sessions.get(iter.next());
				if(session.getId().equals(id)){
				   System.out.println("#"+session.getId());
				   out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a><b>"+session.getId()+"</b></a><br/>");  
				}else{
					System.out.println("*"+session.getId());
				    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a>"+session.getId()+"</a><br/>");  
				}
			}
		}else{
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher .forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);	
	}

}
