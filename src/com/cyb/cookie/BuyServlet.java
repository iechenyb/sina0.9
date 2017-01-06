package com.cyb.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BuyServlet")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BuyServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String basePath = "http://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath() + "/";
		//解决乱码问题  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
      
        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我们店有如下商品：(点击查看详情)<br/>");  
          
        //得到”数据库“数据  
        Map<String, MyObject> map = DB.getAll();  
          
        //展示商品，将商品id通过链接带给ShowObject  
        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;=========================<br/>");  
        for (int i = 1; i < map.size()+1; i++){  
            out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+basePath+"ShowObject?id="+map.get(i+"").getId()+"'>"+map.get(i+"").getName()+"</a><br/>");  
        }  
          
        //显示曾经浏览的商品  
        out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您曾经浏览过的商品：<br/>");  
          
        //得到用户带来的cookie值，返回值是cookie数组  
        Cookie[] cookies = request.getCookies();  
        for (int i = 0; cookies != null && i < cookies.length; i++){  
              
            //找到我们想要的cookie  
            if (cookies[i].getName().equals("historyCookie")){  
                String[] ids = cookies[i].getValue().split("\\,");  
                  
                //得到cookie中存在的id，展现浏览过的商品  
                for (String id : ids){  
                    out.print("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='"+basePath+"ShowObject?id="+id+"'>"+map.get(id).getName()+"</a><br/>");  
                }  
            }  
        }  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response); 
	}

}
class DB{  
    private static LinkedHashMap<String, MyObject> map = new LinkedHashMap<String, MyObject>();  
    static{  
        map.put("1", new MyObject("1", "杯子", "12.0", "烽火牌杯子就是好！"));  
        map.put("2", new MyObject("2", "毛巾", "5.0", "吸水性刚刚的！"));  
        map.put("3", new MyObject("3", "脸盆", "8.0", "容积大，可放衣物多！"));  
        map.put("4", new MyObject("4", "肥皂", "6.0", "强劲去污能力强！"));  
        map.put("5", new MyObject("5", "暖瓶", "18.0", "不保温退货！"));  
    }  
      
    public static Map<String, MyObject> getAll(){  
        return map;  
    }  
}  
class MyObject{  
    
    private String id;  
    private String name;  
    private String price;  
    private String describe;  
      
    public MyObject(String id, String name, String price, String describe) {  
        super();  
        this.id = id;  
        this.name = name;  
        this.price = price;  
        this.describe = describe;  
    }  
      
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getPrice() {  
        return price;  
    }  
    public void setPrice(String price) {  
        this.price = price;  
    }  
    public String getDescribe() {  
        return describe;  
    }  
    public void setDescribe(String describe) {  
        this.describe = describe;  
    }  
}  
