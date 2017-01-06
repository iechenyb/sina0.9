<%@page import="com.cyb.Contanst"%>
<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String pushUrl = Contanst.pushUrl;
%>
<!DOCTYPE html>
<html>
<head>
    <title>netty-socketio-case</title>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
	<script src="socket.io.js"></script>
    <script src="jquery-1.7.2.min.js"></script>
	<script>
		$(function(){
			var socket =  io.connect('<%=pushUrl%>');
			 socket.on("pushpoint", function(data){
				$('#x').text(data.x);
				$('#y').text(data.y);
			});
		});
	</script>
</head>
<body>
	<div id="display" style="height:50px;background-color:gray;">
		<font size=50 >x=<span id="x">0</span>, y=<span id="y">0</span></font>
	</div>
</body>
</html>
