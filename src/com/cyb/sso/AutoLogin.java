package com.cyb.sso;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/AutoLogin")
public class AutoLogin implements Filter { public void destroy() {}  
public void doFilter(ServletRequest req, ServletResponse resp,  
        FilterChain chain) throws IOException, ServletException {  
    System.err.println("开始自动登录验证.....");//此类中应该对登录的servlet直接放行。根据判断url决定。  
    HttpServletRequest requ = (HttpServletRequest) req;  
    HttpSession s = requ.getSession();  
    if (s.getAttribute("user") != null) {//如果用户已经登录则直接放行  
        System.err.println("用户已经登录，没有必须要再做自动登录。。。。");  
    } else {  
        Cookie[] cookies = requ.getCookies();  
        if (cookies != null) {  
            for (Cookie ck : cookies) {  
                if (ck.getName().equals("autologin")) {// 是否是自动登录。。。。  
                    System.err.println("自动登录成功。。。。。");  
                    String val = ck.getValue();  
                    String[] vals = val.split("@");  
                    s.setAttribute("user", vals[0]);  
                }  
            }  
        }  
    }  
    chain.doFilter(req, resp);  
}  
public void init(FilterConfig filterConfig) throws ServletException {}  }
