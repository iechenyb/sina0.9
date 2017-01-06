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
<style>
.pageBox,.pageBox1{ text-align:center; height:25px; padding:15px 0;}
.pageBox .pages a.up,.pageBox .pages a.down{ color:#6eb4ea; text-decoration:none; border:1px solid #ffffff; background:none;}
.pageBox .pages a.else{ background:none; border:none;}
.pageBox .pages a{ padding:3px 7px; border:1px solid #f3f6f6; background:#fdfdfd; margin:0 5px; color:#999999;}
.pageBox .pages a:hover,.pageBox .pages a.hover{ background:#6eb4ea; border:1px solid #6eb4ea; color:#ffffff; text-decoration:none;padding:2px 7px; }
.pageBox .pages span{ padding:3px 7px; color:#999999;}
.fiv_sep{ height:3px; float:left; width:100%; font-size:4px; line-height:2px;}
</style>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script><!--jquery支持1.4以上版本-->
</head>
<body>
<h1>Page</h1>
<div class="pageBox" id="pageBox"></div>
<script type="text/javascript">
function Page(param){	
	if(param!=null&&param!=''&&param!=undefined){
		if(param.curPage!=undefined) this.curPage=param.curPage;      
        if(param.pageSize!=undefined) this.pageSize=param.pageSize;
        if(param.start!=undefined) this.start=param.start;      
        if(param.end!=undefined) this.end=param.end;
        if(param.total!=undefined) this.total =param.total;
        if(param.totalPage!=undefined) this.totalPage=param.totalPage; 
	}
} 
Page.prototype.curPage = 1;//当前页面索引
Page.prototype.pageSize = 10;//每页展示大小
Page.prototype.start = 1;//默认大小
Page.prototype.end = 10;//默认大小
Page.prototype.total = 0;//总记录数
Page.prototype.totalPage = 1;//总页数
Page.prototype.show = function(){
	return this.start+"->"+this.end;
}
Page.prototype.init = function(){
	this.totalPage = this.total/this.pageSize;
	if(this.curPage>this.totalPage) { this.curPage = this.totalPage;}//页数超过最大取最大
	if(this.curPage<1) { this.curPage = 1;}
	this.start = (this.curPage-1)*this.pageSize+1;
	this.end = 2*this.pageSize;
	console.log(this);
}
var page = new Page({total:100,curPage:2});
page.init();
page.show();
</script>
</body>
</html>
