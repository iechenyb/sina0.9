package com.cyb.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.HTML;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.filters.StringFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.cyb.UUIDUtils;
import com.cyb.comconection.ConnectionUtils;

//http://www.hangye5.com/hangyewang/gupiao/22912.html
public class StockUtils {
  public static Log log = LogFactory.getLog(HtmlUtils.class);
  static String shurl = "http://www.hangye5.com/hangyewang/gupiao/22913.html";//1172
  static String szurl = "http://www.hangye5.com/hangyewang/gupiao/22914.html";//1968
  static String hkurl = "http://www.hangye5.com/hangyewang/gupiao/22915.html";//1639 
  public static String cfcenter = "http://quote.eastmoney.com/stocklist.html";//3908
  public static String codeFilePath = "D:";
  public static String codeFileName = "code.html";
  public static String sinaFace = "http://hq.sinajs.cn/list=";
  static String insert = "insert into stock(id_,code_,exchange_,name_,industry,classify,oper_time) values";
  static ConnectionUtils<Stock> dbUtils = null;
  static {
	  dbUtils = new ConnectionUtils<Stock>();	  
  }
  public static void main(String[] args) {
	hangye();
}
  public static void hangye(){
	  String savepath = codeFilePath+File.separator+codeFileName;
	  try {
		   showHtml(shurl, savepath,"<a",false,"SH");
		   showHtml(szurl, savepath,")</a> ",false,"SZ");
		   showHtml(hkurl, savepath,">(",false,"HK");//OVER
     	   showHtml(cfcenter, savepath,"<li><a",false,"CFZX");
     	   FundUtils.initFund();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  
  public static void showHtml(String url,String savepath,String regex,boolean show,String type){
		try {
			File codeFile = new File(savepath);
			log.info("download....");
			HtmlUtils.downLoadFromUrl(url,savepath);
			log.info("download over....");
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(codeFile),"GBK"));
			String content = "";
			int count =0;
			while((content = reader.readLine())!=null){
				
				if(content.contains(regex)){
					if(show){
						log.info(content);
					}
					count++;
					if("HK".equals(type)){
						reslovehk(content);
					}else if("SH".equals(type)){
						reslovesh(content);
					}else if("SZ".equals(type)){
						reslovesz(content);
					}else if("CFZX".equals(type)){
						resolvecfzx(content);
					}
				}
			}
			log.info("一共"+count+"个节点！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  public static String whichExchange(String name,String code,String exchange){
	  String delesql = "delete FROM STOCK where code_='"+code+"' and exchange_='"+exchange+"'";
	  dbUtils.update(delesql);
	  String insertsql = insert+" ('"+UUIDUtils.getUUID()+"','"+code+"','"+exchange+"','"+name+"','"+exchange+"','"+exchange+"',sysdate)";
	  log.info(insertsql);
	  dbUtils.update(insertsql);
	  return exchange+code;	 
  } 
  public static Map<String,String> reslovehk(String node){
	  	Map<String,String> data = new HashMap<String, String>();
	      Parser parser = Parser.createParser(node, "gbk");	
	      RegexFilter filter = new RegexFilter("[<a]*");
	      try {
				NodeList tds = parser.extractAllNodesThatMatch(filter);
				for(int i=0;i<tds.size();i++){
					String content =tds.elementAt(i).getText().replace(" ", "") ;
					//(00006)电能实业
					if(!"".equals(content)){
						String name = content.substring(content.indexOf(")")+1,content.length());
						String code = content.substring(content.indexOf("(")+1,content.indexOf(")"));
						String exchange = "hk";
						whichExchange(name,code,exchange);
					}
				}
		 } catch (Exception e) {
				e.printStackTrace();
		}
	      return data;
}
  public static void stringFilter(String url,String encoding,String containStr){
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
          NodeList list = parser.extractAllNodesThatMatch(filter);
          for(int i=0; i<list.size();i++){
              Node node = (Node)list.elementAt(i);
              System.out.println("link is :" + node.toHtml());
          }
          
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

public static Map<String,String> reslovesh(String node){
  	Map<String,String> data = new HashMap<String, String>();
      Parser parser = Parser.createParser(node, "gbk");	
      RegexFilter filter = new RegexFilter("[<a]*");
      String content ="";
      try {
			NodeList tds = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<tds.size();i++){
				 content=tds.elementAt(i).getText().replace(" ", "") ;
				if(!"".equals(content)){
					//精伦电子(600355)
					String name = content.substring(0,content.indexOf("("));
					String code = content.substring(content.indexOf("(")+1,content.indexOf(")"));
					String exchange = "sz";
					whichExchange(name,code,exchange);
				}
			}
	 } catch (Exception e) {
			log.info("EXCEPTION ["+content+"],"+e.getMessage());
	}
      return data;
  }  

public static Map<String,String> reslovesz(String node){
  	Map<String,String> data = new HashMap<String, String>();
      Parser parser = Parser.createParser(node, "gbk");	
      RegexFilter filter = new RegexFilter("[<a]*");
      String content ="";
      try {
			NodeList tds = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<tds.size();i++){
				content = tds.elementAt(i).getText().replace(" ", "") ;
				if(!"".equals(content)){
					//深华新(000010)
					String name = content.substring(0,content.indexOf("("));
					String code = content.substring(content.indexOf("(")+1,content.indexOf(")"));
					String exchange = "sz";
					whichExchange(name,code,exchange);
				}
			}
	 } catch (Exception e) {
		 log.info("EXCEPTION ["+content+"],"+e.getMessage());
	}
      return data;
  }  
public static Map<String,String> resolvecfzx(String li){
	Map<String,String> data = new HashMap<String, String>();
    Parser parser = Parser.createParser(li, "gbk");	
    RegexFilter filter = new RegexFilter("[<a]*");
    try {
		NodeList tds = parser.extractAllNodesThatMatch(filter);
		Node a  = null ;
		String exchange = "" ;
		String code = "" ;
		String name = "" ;
		String codeInfor = "";
		for(int i=0;i<tds.size();i++){
			try {
				a = tds.elementAt(i);
				if(a.getParent()!=null){
					String aHtml = a.getParent().toHtml();//<a target='_blank' href='http://quote.eastmoney.com/sz300447.html' >全信股份(300447)</a>
					List<String> list = HtmlUtils.match(aHtml.replace(">", " >"), "a","href");
					String href = list.get(0);//http://quote.eastmoney.com/sz300447.html
					codeInfor = href.substring(href.lastIndexOf("/") + 1, href.length() - 5);
					exchange = codeInfor.substring(0, 2);
					code = codeInfor.substring(2, codeInfor.length());
				}
				String aTips = a.toHtml();//全信股份(300447)
				name = aTips.substring(0, aTips.lastIndexOf("("));
				if(!"".equals(codeInfor)){
					if(code!=null&&!"".equals(code)&&!"null".equals(code)){
						data.put("name", name);
						data.put("code", code);
						data.put("exchange", exchange);
						whichExchange(name,code,exchange);
					}		
				}		
			} catch (Exception e) {
				log.info("EXCEPTION ["+codeInfor+"],"+e.getMessage());
			}
		}
	} catch (ParserException e) {
		e.printStackTrace();
	}
    return data;
}
}
