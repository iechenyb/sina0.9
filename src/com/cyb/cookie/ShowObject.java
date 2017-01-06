package com.cyb.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ShowObject")
public class ShowObject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowObject() {
        super();
    }
    public void init(ServletConfig config) throws ServletException {
    	 super.init(config); 
    	/* <init-param>
    	 <param-name>SSOServiceURL</param-name>
    	 <param-value>http://wangyu.prc.sun.com:8080/SSOAuth/SSOAuth</param-value>
    	 </init-param>*/
    	 //config.getInitParameter("SSOServiceURL");//读取xml配置的key值
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //解决乱码问题  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
          
        //得到Url中的id  
        String id = request.getParameter("id");  
          
        //获取”数据库“中数据  
        Map<String, MyObject> map = DB.getAll();  
        //根据id得到浏览的商品  
        MyObject myobj = (MyObject) map.get(id);  
          
        out.write("你要看的商品是："+myobj.getName()+"<br/>");  
        out.write("价格："+myobj.getPrice()+"<br/>");  
        out.write("对该商品的描述是：<br/>");  
        out.write(myobj.getDescribe()+"<br/>");  
        out.write("<a href='index.jsp'>返回首页</a><br/>");  
        //给浏览器回写cookie  
        String cookieValue = buildCookie(id, request);//产生想要的cookie中的值  
        Cookie cookie = new Cookie("historyCookie", cookieValue);  
        cookie.setMaxAge(1*24*3600);  
        System.out.println(request.getContextPath());
        cookie.setPath(request.getContextPath());  
        response.addCookie(cookie);  
	}
	private String buildCookie(String id, HttpServletRequest request) {  
        
        String historyCookie = null;  
          
        //得到请求中带来的cookie值  
        Cookie[] cookies = request.getCookies();  
        for (int i = 0; cookies != null && i < cookies.length; i++){  
            if (cookies[i].getName().equals("historyCookie") ){  
                historyCookie = cookies[i].getValue();  
            }  
        }  
          
        //如果为空返回当前商品的id  
        if (historyCookie == null){  
            return id;  
        }  
          
        LinkedList<String> list = new LinkedList<String>( Arrays.asList((historyCookie.split("\\,"))));  
          
        //对不同的情况进行分析返回id的值  
        if (list.contains(id)){  
            list.remove(id);  
        }else{  
            if (list.size() >= 5){  
                list.removeLast();  
            }  
        }  
        list.addFirst(id);  
          
        StringBuffer sb = new StringBuffer();  
        for (String sid : list){  
            sb.append(sid + ",");  
        }  
        sb.deleteCharAt(sb.length()-1);  
          
        return sb.toString();  
    }  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response); 
	}

}
