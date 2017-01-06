package com.cyb.html.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.cyb.file.FileUtils;


public class CffexggUtils {
	public static List<Map<String,Object>> ggs = new ArrayList<Map<String,Object>>();
	public static List<String> dates = new ArrayList<String>();
	public static void main(String[] args) {
		String cffexggPage = "http://www.cffex.com.cn/tzgg/jysgg/index.html";
		NodeFilter filter = null;
		
		filter = new HasAttributeFilter( "class","gray_fy");//获取日期
		try {
			HTMLParserDate(filter, cffexggPage);
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		filter = new HasAttributeFilter( "class","fytitle");
		try {
			HTMLParserTitle(filter, cffexggPage);//获取标题和连接
		} catch (ParserException e) {
			e.printStackTrace();
		}
		String data = JSONArray.fromObject(ggs).toString();
		FileUtils.overideString2File(data, "d:/cffexggs.txt");
	}
	 public static void HTMLParserTitle(NodeFilter filter,String url) throws ParserException{
		 Parser parser = new Parser (url);
		 NodeList  list = parser.extractAllNodesThatMatch(filter);
		 Map<String,Object> map = null;
		 for(int i=0;i<list.size();i++){
			 map = new HashMap<String, Object>();
			 String html = list.elementAt(i).toHtml();
			 String title=list.elementAt(i).toPlainTextString();
			 String ggurl = html.substring(html.indexOf("(")+3, html.indexOf(")")-1);
			 map.put("title", title);
			 map.put("ggurl", ggurl);
			 map.put("date", dates.get(i));
			 {
			    String gg = "http://www.cffex.com.cn/tzgg/jysgg/"+ggurl;
				filter = new HasAttributeFilter( "class","TRS_Editor");
				Parser parser1;
				try {
					parser1 = new Parser (gg);
					NodeList  list1 = parser1.extractAllNodesThatMatch(filter);
				    for(int i1=0;i1<list1.size();i1++){
					 map.put("content",list1.elementAt(i1).toHtml());
				    }
				} catch (ParserException e) {
					e.printStackTrace();
				}
				}
			 ggs.add(map);
		 }
	 }
	 public static void HTMLParserDate(NodeFilter filter,String url) throws ParserException{
		 Parser parser = new Parser (url);
		 NodeList  list = parser.extractAllNodesThatMatch(filter);
		 for(int i=0;i<list.size();i++){
			 String html = list.elementAt(i).toPlainTextString();
			 dates.add(html);
		 }
	 }
}
