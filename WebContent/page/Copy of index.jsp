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
var Page = {
     /**
        displayId//默认值显示区域Id为pageBox，可以不填
        pagesize//每页条数，默认是15条，可以不填         
        totalsize//总条数
        curpage//当前页数
        simple//默认是false，true没有上一页和下一页，
        type//0默认走http跳转，1是jsp页面必须有pageCallBack(pageNum)函数,从1开始
        url//链接地址，如果type出入1此处就可以不填
        例子：Page._run({totalsize:300,curpage:11,type:1,simple:true}
        */
    _run:function(param){
        var totalpages = 1,//总页数
            displayId="#pageBox",//显示区域Id  
            pagesize=15,//每页条数         
            totalsize=0,//总条数
            curpage=1,//当前页数   
            url="",//链接地址      
            type=0,//0默认走http跳转，1传入回调函数
            simple=false;//简单版本，没有上一页和下一页
        if(param.type!=undefined)type=param.type;      
        if(param.displayId!=undefined)displayId=param.displayId;
        if(param.pagesize!=undefined)pagesize=param.pagesize;      
        if(param.totalsize!=undefined)totalsize=param.totalsize;
        if(param.curpage!=undefined)curpage=param.curpage;
        if(param.url!=undefined)url=param.url; 
        if(param.simple!=undefined)simple=param.simple;
        if(url.indexOf("?")==-1){
            url += "?1=1";
        }
        if(totalsize>0){
            totalpages = Page._getTotalPages(totalsize,pagesize);
            if(curpage>totalpages){curpage=totalpages;}//传入页数大于总页数，就按最后一页算
            if(totalpages>1){               
                var firstPage= simple?"":Page._builderPageArea(type,"up",url,curpage-1,"上一页",false,displayId),
                lastPage = simple?"":Page._builderPageArea(type,"down",url,parseInt(curpage)+1,"下一页",false,displayId),pages = new Array();
                if(curpage<=4){//第一页 无上一页
                    if(curpage!=1){pages.push(firstPage);}
                    if(totalpages>5){//总页数超过5
                        for(var i=1;i<=5;i++){
                            if(curpage==i){
                                pages.push(Page._builderPageArea(type,"",url,i,i,true,displayId));                             
                            }else{
                                pages.push(Page._builderPageArea(type,"",url,i,i,false,displayId));
                            }
                        }
                        pages.push('<span>...</span>');
                        pages.push(Page._builderPageArea(type,"",url,totalpages,totalpages,false,displayId));                  
                    }else{//总页数<=5的，列1,2，3,4，5
                        for(var i=1;i<=totalpages;i++){
                            if(curpage==i){
                                pages.push(Page._builderPageArea(type,"",url,i,i,true,displayId));
                            }else{
                                pages.push(Page._builderPageArea(type,"",url,i,i,false,displayId));
                            }                      
                        }
                    }              
                    if(curpage!=totalpages)pages.push(lastPage);
                }else if(totalpages-curpage<=4){//最后一页 无下一页                 
                    if(curpage!=1){pages.push(firstPage);}
                    if(totalpages>5){//总页数超过5
                        pages.push(Page._builderPageArea(type,"",url,1,1,false,displayId));
                        pages.push('<span>...</span>');
                        for(var i=totalpages-4;i<=totalpages;i++){
                            if(curpage==i){
                                pages.push(Page._builderPageArea(type,"",url,i,i,true,displayId));
                            }else{
                                pages.push(Page._builderPageArea(type,"",url,i,i,false,displayId));
                            }
                        }  
                        if(totalpages!=curpage) {pages.push(lastPage);}            
                    }else{//总页数<=5的，列1,2，3,4，5
                        for(var i=1;i<=totalpages;i++){
                            if(curpage==i){
                                pages.push(Page._builderPageArea(type,"",url,i,i,true,displayId));
                            }else{
                                pages.push(Page._builderPageArea(type,"",url,i,i,false,displayId));
                            }                                                  
                        }
                                if(curpage!=totalpages)pages.push(lastPage);
                    }          
                }else{//有上一页和最后一页 且总页数肯定大于5            
                    pages.push(firstPage); 
                    pages.push(Page._builderPageArea(type,"",url,1,1,false,displayId));
                    pages.push('<span>...</span>');
                    for(var i=curpage-2;i<=curpage+2;i++){
                        if(curpage==i){
                            pages.push(Page._builderPageArea(type,"",url,i,i,true,displayId));     
                        }else{
                            pages.push(Page._builderPageArea(type,"",url,i,i,false,displayId));    
                        }      
                    }  
                    pages.push('<span>...</span>');
                    pages.push(Page._builderPageArea(type,"",url,totalpages,totalpages,false,displayId));
                    pages.push(lastPage);
                }
                var result = new Array();              
                result.push('<div class="pages">');
                result.push(pages.join(''));
                result.push('</div>');   
                $('pageBox').html(result.join(''));
            }          
        }else{
        }      

    },
    /**计算总页数*/
    _getTotalPages:function(_totalsize,_pagesize){     
        if(_totalsize%_pagesize==0)
            return _totalsize/_pagesize;
        else
            return parseInt(_totalsize/_pagesize)+1;           
    },
    /**构造分页的每个页数区域*/
    _builderPageArea:function(type,textType,url,page,text,_focus,_displayId){  
        var hrefStr,href= new Array();
        if(type==0){
            href.push(url);
            href.push('&pagenum=');
            href.push(page);
        }else if(type==1){
           /*  href.push('javascript:void(0);pageCallBack(\\');
            href.push(page);
            href.push('\\','');
            href.push(_displayId);
            href.push('\\')'); */
        }
        hrefStr = href.join(''),result=new Array();    
        if(textType=='up'){
                result.push('<a href="');
                result.push(hrefStr);
                result.push('" class="up">上一页</a>');              
        }else if(textType=='down'){
                result.push('<a href="');
                result.push(hrefStr);
                result.push('" class="down">下一页</a>');
        }else{
                result.push('<a href="');
                result.push(hrefStr);
                if(_focus){
                    result.push('" style="background:#6EB4EA;color:#FFF;">');
                }else{
                    result.push('">');
                }
                result.push(page);
                result.push('</a>');
        }
        return result.join('');
    }  
};
Page._run({totalsize:100,curpage:1,pagesize:10});

function Person(param){	
	if(param!=null&&param!=''){
		this.password = param.password;
		this.username = param.username;
	}
} 
//Person={};
Person.prototype.username = "zhangsan";
Person.prototype.password = "123";
Person.prototype.getInfo = function(){
	return this.username + ", " + this.password;
}
var person = new Person({password:'456',username:'chenyb'});
var person2 = new Person(null);
console.log(person.getInfo());
console.log(person2.getInfo());
</script>
</body>
</html>
