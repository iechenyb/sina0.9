package com.cyb.sso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)  
        throws ServletException, IOException {  
    HttpSession s = req.getSession();       //获取Session  
    Cookie cookie = new Cookie("autologin","");//必须声明一个完全相同名称的Cookie  
    cookie.setPath("/");//路径也要完全相同  
    cookie.setDomain(".itcast.com");//域也要完全相同  
    cookie.setMaxAge(0);//设置时间为0,以直接删除Cookie  
    resp.addCookie(cookie);  
    s.removeAttribute("user");  
    System.err.println("安全退出。。。。。");  
    resp.sendRedirect(req.getContextPath()+"/index.jsp");  
}  }
