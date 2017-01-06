package com.cyb.html.usecase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.cyb.dom.Html2DomUtils;
import com.cyb.file.FileUtils;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;


public class ShfeggUtils {
	public static List<Map<String,Object>> ggs = new ArrayList<Map<String,Object>>();
	public static List<String> dates = new ArrayList<String>();
	public static void main(String[] args) {
		String shfeggPage = "http://www.shfe.com.cn/news/notice/";
		NodeFilter filter = null;
		filter = new TagNameFilter("ul");
		try {
			HTMLParserData(filter, shfeggPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*String data = JSONArray.fromObject(ggs).toString();
		FileUtils.overideString2File(data, "d:/shfeggs.txt");*/
	}
	 public static String getContent(String url) throws ParserException{
		 Parser parser = new Parser (url);
		 NodeFilter filter = new HasAttributeFilter("class", "article-detail-text");
		 NodeList  list = parser.extractAllNodesThatMatch(filter);
		 String html ="";
		 for(int i=0;i<list.size();i++){
			 html = list.elementAt(i).toHtml();
			 html =list.elementAt(i).getChildren().elementAt(7).toHtml();
			 /*NodeList child = list.elementAt(i).getChildren();
			 for(int j=0;j<child.size();j++){
				 if(child.elementAt(j).toHtml()!=""){
					 System.out.println(j+"xx:"+child.elementAt(j).toHtml());
				 }
			 }*/
			 System.out.println(html);
		 }
		 return html;
	 }
	 public static void HTMLParserData(NodeFilter filter,String url) throws ParserException, SAXException, IOException{
		 Parser parser = new Parser (url);
		 NodeList  ul = parser.extractAllNodesThatMatch(filter);
		 for(int i=0;i<1;i++){//获取第一个ul列表
			 String html = ul.elementAt(i).toHtml();
			 Html2DomUtils.genStandardHtml("d:/tmp.html", html+"\n");
		 }
		  //创建一个解析器    
	        DOMParser parser1 = new DOMParser();    
	        //解析HTML文件  
	        parser1.parse("d:/tmp.html");  //必须有html，body标签
	        //获取解析后的DOM树    
	        Document document = parser1.getDocument();    
	        //通过getElementsByTagName获取Node    
	        org.w3c.dom.NodeList nodeList = document.getElementsByTagName("li");
	        Map<String,Object> map = null;
	        for (int i = 0; i < nodeList.getLength(); i++) {    
	            Element e = (Element)nodeList.item(i);    
	            //System.out.print(e.getAttribute("href") + "\t");    
	            System.out.println(e.getTextContent()); 
	            map = new HashMap<String,Object>();
	            Node node = e.getChildNodes().item(1);
	            map.put("date", e.getChildNodes().item(0).getTextContent().replace("[", "").replace("]", ""));
	            map.put("title", node.getAttributes().getNamedItem("title").getNodeValue());
	            map.put("ggurl", "http://www.shfe.com.cn"+node.getAttributes().getNamedItem("href").getNodeValue());
	            map.put("content",getContent(map.get("ggurl").toString()));
	            ggs.add(map);
	        }  
	        String data = JSONArray.fromObject(ggs).toString();
			FileUtils.overideString2File(data, "d:/shfeggs.txt");
	 }
}
