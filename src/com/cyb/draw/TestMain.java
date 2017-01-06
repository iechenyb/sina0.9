package com.cyb.draw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
//对接口进行测试
public class TestMain {
	private static  String url = "http://www.gezhongji.com/note/show/30956/";
	private String charset = "utf-8";
	private HttpClientUtil httpClientUtil = null;
	
	public TestMain(){
		httpClientUtil = new HttpClientUtil();
	}
	
	public  void test(String rdm,String pwd){
		String httpOrgCreateTest = url;
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("csrf_test_name",rdm);
		createMap.put("show_pwd",pwd);
		String httpOrgCreateTestRtn = httpClientUtil.doPost(httpOrgCreateTest,createMap,charset);
		System.out.println("result:"+httpOrgCreateTestRtn);
	}
	 public static Map<String,String> reslovehk(String node){
		  	  Map<String,String> data = new HashMap<String, String>();
		      Parser parser = Parser.createParser(node, "gbk");	
		      RegexFilter filter = new RegexFilter("[<input type='hidden']*");
		      try {
					NodeList tds = parser.extractAllNodesThatMatch(filter);
					for(int i=0;i<tds.size();i++){
						String content =tds.elementAt(i).getText().replace(" ", "") ;
						System.out.println("####:"+content);
					}
			 } catch (Exception e) {
					e.printStackTrace();
			}
		      return data;
	}
	   public static String nodeFilterTagName(String url,String encoding,String tagName){
		   String res = "";
		   try {
	            Parser parser = new Parser();
	            parser.setURL(url);
	            if(null==encoding){
	                parser.setEncoding(parser.getEncoding());
	            }else{
	                parser.setEncoding(encoding);
	            }
	            //过滤页面中的链接标签
	            TagNameFilter filter = new TagNameFilter(tagName);
	            NodeList list = parser.extractAllNodesThatMatch(filter);
	            for(int i=0; i<list.size();i++){
	            	if(i==0){
		                Node node = (Node)list.elementAt(i);
		                //System.out.println("link is :" + node.toHtml());
		                String sx = new String(node.toHtml().trim());
		                System.out.println(sx.substring(sx.lastIndexOf("=")+2,sx.length()-3-1));
		                res =  sx.substring(sx.lastIndexOf("=")+2,sx.length()-3-1);
	                }
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		   return res;
	    }
	   /*public static void stringFilter(String url,String encoding,String containStr){
	        try {
	            Parser parser = new Parser();
	            parser.setURL(url);
	            if(null==encoding){
	                parser.setEncoding(parser.getEncoding());
	            }else{
	                parser.setEncoding(encoding);
	            }
	            //OrFilter是结合几种过滤条件的‘或’过滤器
	            NodeFilter filter = new StringFilter(containStr);
	            NodeList list = parser.extractAllNodesThatMatch((org.htmlparser.NodeFilter) filter);
	            for(int i=0; i<list.size();i++){
	                Node node = (Node)list.elementAt(i);
	                System.out.println("link is1 :" + node.toHtml());
	                String sx = node.toHtml();
	                System.out.println(sx.substring(12, 18));
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }*/
	   public static String sendPost(String url, String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            URLConnection conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }    
	public static void main(String[] args){
		TestMain main = new TestMain();
		for(int i=0;i<100;i++){
			String r = nodeFilterTagName(url, "UTF-8", "input");
			main.test(r,"2306");
		}
	}
}
