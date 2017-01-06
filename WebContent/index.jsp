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
   <a href="cookies.jsp">cookie&Session</a>
    <a href="push/index.jsp">推送案例</a>
     <a href="sina.jsp">数据抓取测试</a>
      <a href="#">socket</a>
      <a href="http://localhost:9797/">jmx</a>
       <a href="jndi.jsp">jndi测试</a>
</body>
</html>