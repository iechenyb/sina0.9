package com.cyb.html.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;

import com.cyb.file.FileUtils;


public class CzceggUtils {
	public static Map tag=null;
	public static List<Map<String,Object>> ggs = new ArrayList<Map<String,Object>>();
	public static List<String> dates = new ArrayList<String>();
	public static void main(String[] args) {
		String czcexggPage = "http://www.czce.com.cn/portal/jysdt/ggytz/A090601index_1.htm";
		NodeFilter filter = null;
		filter = new HasAttributeFilter( "class","listtab");
		try {
			HTMLParserTitle(filter, czcexggPage);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		String data = JSONArray.fromObject(ggs).toString();
		FileUtils.overideString2File(data, "d:/czcexggs.txt");
	}
	 public static void HTMLParserTitle(NodeFilter filter,String url) throws ParserException{
		 Parser parser = new Parser (url);
		 parser.setEncoding("GB2312");
		 NodeList  table = parser.extractAllNodesThatMatch(filter);
		 Map<String,Object> map = null;
		 for(int i=0;i<table.size();i++){
			 map = new HashMap<String, Object>();
			 NodeList body = table.elementAt(i).getChildren();
			 for(int j=0;j<body.size();j++){
				 NodeList trs = body.elementAt(j).getChildren();
				 if(trs!=null){
					 for(int k=0;k<trs.size();k++){
						 NodeList tds = trs.elementAt(k).getChildren();
						 System.out.println(tds.elementAt(0).toHtml());
						 if(k==0){
							 Map<String,String> res=anaNode(tds.elementAt(0).toHtml());
							 if(tag!=null){
								 map.put("title", res.get("title"));
								 map.put("ggurl", "http://www.czce.com.cn"+res.get("href"));
							 }
						 }else{
							 map.put("date", tds.elementAt(0).toHtml());
						 }
						 
					 }
					 ggs.add(map);
				 }
			 }
			 map=null;
		 }
	 }
	public static Map<String,String> anaNode(String resource){
		 try{
			  Parser parser = new Parser (resource);
			  NodeVisitor visitor = new NodeVisitor(true, true) {
	                @SuppressWarnings("unchecked")
					public void visitTag(Tag tag) {
	                   if(tag.getTagName().equals("A")){
	                	   CzceggUtils.tag = new HashMap<String,String>();
		                   CzceggUtils.tag.put("title", tag.getAttribute("title"));
		                   CzceggUtils.tag.put("href", tag.getAttribute("href"));
	                   }
	                }
	          };
	          parser.visitAllNodesWith(visitor);
	        }catch( Exception e ) {     
	            e.printStackTrace();
	        }
		 return  CzceggUtils.tag;
	}
}
