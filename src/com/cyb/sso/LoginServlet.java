package com.cyb.sso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        doPost(req, resp);  
    }  
    public void doPost(HttpServletRequest req, HttpServletResponse resp)  
            throws ServletException, IOException {  
        String nm = req.getParameter("name");  
        String pwd = req.getParameter("pwd");  
        String chk = req.getParameter("chk");   //是否选中了7天自动登录  
        String forward = "/index.jsp";  
        if(nm!=null && !nm.trim().equals("") && nm.startsWith("it")//用户名是it开始，且密码是pwd开始的可以登录  
                && pwd !=null && !pwd.trim().equals("") &&  
                pwd.startsWith("pwd")){  
            System.err.println("登录成功。。。。。");  
            forward = "/jsps/welcome.jsp";  
            //无论如何，都要设置cookie,如果没有选择自动登录，则只在当前页面的跳转时有效，否则设置有效期间为7天。  
            Cookie cookie = new Cookie("autologin",nm+"@"+pwd);  
            cookie.setPath("/");                //如果路径为/则为整个tomcat目录有用  
            cookie.setDomain(".itcast.com");    //设置对所有*.itcast.com为后缀的域名效  
            if(chk!=null){  
                int time = 1*60*60*24*7;    //1秒*60=1分*60分=1小时*24=1天*7=7天  
                cookie.setMaxAge(time);  
            }  
            resp.addCookie(cookie);  
            req.getSession().setAttribute("user", nm);  
        }else{  
            System.err.println("登录不成功。。。。。。");  
        }  
        req.getRequestDispatcher(forward).forward(req, resp);  
    }  
}