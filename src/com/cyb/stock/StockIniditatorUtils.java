package com.cyb.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Parser;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.util.NodeList;

import com.cyb.UUIDUtils;
import com.cyb.comconection.ConnectionUtils;
import com.cyb.mybatis.User;
//http://quote.stockstar.com/stock/ab_sz.shtml
public class StockIniditatorUtils {
  public static Log log = LogFactory.getLog(HtmlUtils.class);
  static String sinaurl = "http://money.finance.sina.com.cn/fund/view/vNewFund_FundListings.php";//基金列表4209
  public static String codeFilePath = "D:";
  public static String codeFileName = "code.html";
  public static String sinaFace = "http://hq.sinajs.cn/list=";
  static String insert = "insert into stock(id_,code_,exchange_,name_,industry,classify,oper_time) values";
  static ConnectionUtils<Stock> dbUtils = null;
  static {
	  dbUtils = new ConnectionUtils<Stock>();	  
  }
  public static void main(String[] args) {
	  System.out.println("varhq_str_hk00081".substring(10,"varhq_str_hk00081".length()));
	  System.out.println("varhq_str_hk00081".substring(10,12));
	  initFund();
}
  public static void initFund(){
	  String savepath = codeFilePath+File.separator+codeFileName;
	  try {
		  showHtml(sinaurl, savepath,"<tr><td><a",false);
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  public static void showHtml(String url,String savepath,String regex,boolean show){
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
					count++;
					if(show){
						log.info(content);
					}
					reslove(content);
				}
			}
			log.info("一共"+count+"个节点！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
  public static String whichExchange(String content) throws SQLException{
	  String name = content.substring(0,content.indexOf("("));
	  String code = content.substring(content.indexOf("(")+1,content.indexOf(")"));
	  String datash = HtmlUtils.grabJsonDataFromURL(sinaFace+"sh"+code);
	  String datasz = HtmlUtils.grabJsonDataFromURL(sinaFace+"sz"+code);
	  String exchange = "";
	  String querysql = " select * FROM STOCK where code_='"+code+"'";
	  List<Stock> stocks =  dbUtils.queryForMap(querysql,Stock.class);
	  if(stocks.size()>0){
		  return "";
	  }else{
		  if(datash.split("=")[1].length()>5){
			  log.info(name+" is shanghai");
			  exchange = "sh";
		  }else if(datasz.split("=")[1].length()>5){
			  log.info(name+" is shenzhen");
			  exchange = "sz";
		  }else{
			  log.info(name+" is null");
			  exchange = "null";
		  } 
		  if(!"".equals(exchange)){
			  String delesql = "delete FROM STOCK where code_='"+code+"' and exchange_='"+exchange+"' and name_='"+name.trim()+"'";
			  dbUtils.update(delesql);
			  String insertsql = insert+" ('"+UUIDUtils.getUUID()+"','"+code+"','"+exchange+"','"+name+"','fund','fund',sysdate)";
			  dbUtils.update(insertsql);
		  }
	  }
	  return exchange+code;	 
  } 
  public static Map<String,String> reslove(String node){
	  	Map<String,String> data = new HashMap<String, String>();
	      Parser parser = Parser.createParser(node, "gbk");	
	      RegexFilter filter = new RegexFilter("[<a]*");
	      String content = "";
	      try {
				NodeList tds = parser.extractAllNodesThatMatch(filter);
				System.out.println(tds.size());
				for(int i=0;i<tds.size();i++){
					log.info(tds.elementAt(i).getText());
					content = tds.elementAt(i).getText().trim();
					if(content!=null&&!"".equals(content)){
						whichExchange(content);
					}
				}
		 } catch (Exception e) {
			 System.out.println("Exception ["+content+"]");
		}
	      return data;
	  }  
}
