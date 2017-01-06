<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String basePath = "http://" + request.getServerName() + ":"
		+ request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>default</title>
</head>
<body>
   <hr>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cookie测试目的：展示用户已经访问过的商品信息到浏览器，清除浏览器缓存可以将商品的信息从cookie中去掉（cookie消息不能夸跨浏览器）
   <a href='<%=basePath%>ShowObject?id=1' target='_self'>访问商品id=1</a>
   <a href='<%=basePath%>BuyServlet' target='_self'>展示商品列表及历史cookie</a><br>
   <hr>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Session测试目的，同样不能跨浏览器，<a href='<%=basePath%>SessionEnter?cmd=login' target='_self'>登录</a>
   <a href='<%=basePath%>SessionEnter?cmd=out' target='_self'>退出</a>
    <a href='<%=basePath%>SessionEnter?cmd=out1&jsessionid=1' target='_self'>清除浏览器缓存之后，将原来的sessionid传给服务器，找回session</a>
   <%=request.getSession().getId()%>,<%=request.getSession().getAttribute("name")%>
   <a href='index.jsp' target='_self'>刷新</a><br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;这是一个保险措施 ,因为Session默认是需要Cookie支持的 但有些客户浏览器是关闭Cookie的 这个时候就需要在URL中指定服务器上的session标识,也就是5F4771183629C9834F8382E23BE13C4C 
用一个方法(忘了方法的名字)处理URL串就可以得到这个东西 这个方法会判断你的浏览器是否开启了Cookie,如果他认为应该加他就会加上去 .<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;URL重写功能,为了防止一些用户把Cookie禁止而无法使用session而设置的功能.jsessionid后面的一长串就是你服务器上的session的ID号,这样无需cookie也可以使用session. <br>
sessionid是作为一个临时cookie放在浏览器端的。 session的具体信息放在服务器端。 每次浏览器发出的请求，都会在http header里 带上 sessionid来标识自己。 <br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;清除浏览器缓存后，会重新生成一个sessionid，即jsessionid。表象存储在session中的姓名会丢失，原因是sessionid发生修改，找不到原来的session导致。可以调试看cookie信息.<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;没有 HttpServletRequest.getSession(true) ，Sesion也是会创建的，比如直接访问某个jsp，但是访问html就不会创建啦.<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Session存储在服务器端，一般为了防止在服务器的内存中（为了高速存取），Sessinon在用户访问第一次访问服务器时创建，需要注意只有访问JSP、Servlet等程序时才会创建Session，只访问HTML、IMAGE等静态资源并不会创建Session，可调用request.getSession(true)强制生成Session。<br>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注意：新开的浏览器窗口会生成新的Session，但子窗口除外。子窗口会共用父窗口的Session。例如，在链接上右击，在弹出的快捷菜单中选择"在新窗口中打开"时，子窗口便可以访问父窗口的Session。<br>
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注意：TOMCAT判断客户端浏览器是否支持Cookie的依据是请求中是否含有Cookie。尽管客户端可能会支持Cookie，但是由于第一次请求时不会携带任何Cookie（因为并无任何Cookie可以携带），URL地址重写后的地址中仍然会带有jsessionid。当第二次访问时服务器已经在浏览器中写入Cookie了，因此URL地址重写后的地址中就不会带有jsessionid了。<br>
   <hr>
</body>
</html>