package com.cyb.stock;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.RegexFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


public class HtmlUtils {
	public static Log log = LogFactory.getLog(HtmlUtils.class);
	/**
	 * 下载stock html文件
	 */
	public static void downCodeFromUrl(String url,String savepath){
		try {
			
			File codeFile = new File(savepath);
			if(!codeFile.exists()){
				log.info("download....");
				downLoadFromUrl(url,savepath);
				log.info("download over....");
			}
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(codeFile),"GBK"));
			String content = "";
			int count =0;
			while((content = reader.readLine())!=null){
				if(content.contains("<li><a")){
					log.info(content);
					count++;
					log.info(getCodeInfor(content));
				}
			}
			log.info("一共"+count+"个节点！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void showHtml(String url,String savepath,String regex,boolean show){
		try {
			File codeFile = new File(savepath);
			log.info("download....");
			downLoadFromUrl(url,savepath);
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
	public static void resolveCodeFromHtmlFile(String htmlPath){
		try {
			File codeFile = new File(htmlPath);
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(codeFile),"GBK"));
			String content = "";
			int count =0;
			while((content = reader.readLine())!=null){
				if(content.contains("<li><a")){
					//log.info(content);
					count++;
					//log.info(getCodeInfor(content));
				}
			}
			log.info("一共"+count+"个节点！");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 public static void  downLoadFromUrl(String urlStr,String savePath) throws IOException{
	        URL url = new URL(urlStr);  
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	                //设置超时间为3秒
	        conn.setConnectTimeout(3*1000);
	        //防止屏蔽程序抓取而返回403错误
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

	        //得到输入流
	        InputStream inputStream = conn.getInputStream();  
	        //获取自己数组
	        byte[] getData = readInputStream(inputStream);    

	        //文件保存位置
	        File file = new File(savePath);    
	        FileOutputStream fos = new FileOutputStream(file);     
	        fos.write(getData); 
	        if(fos!=null){
	            fos.close();  
	        }
	        if(inputStream!=null){
	            inputStream.close();
	        }
	        log.info("info:"+url+" download success"); 
	    }
	 public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
	        byte[] buffer = new byte[1024];  
	        int len = 0;  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
	        while((len = inputStream.read(buffer)) != -1) {  
	            bos.write(buffer, 0, len);  
	        }  
	        bos.close();  
	        return bos.toByteArray();  
	    }  
  public static List<String> match(String source, String element, String attr) {
			List<String> result = new ArrayList<String>();
			String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?\\s.*?>";
			Matcher m = Pattern.compile(reg).matcher(source);
			while (m.find()) {
				String r = m.group(1);
				result.add(r);
			}
			return result;
	}
  public static Map<String,String> reslove(String node){
	  	Map<String,String> data = new HashMap<String, String>();
	      Parser parser = Parser.createParser(node, "gbk");	
	      RegexFilter filter = new RegexFilter("[<a]*");
	      try {
				NodeList tds = parser.extractAllNodesThatMatch(filter);
				System.out.println(tds.size());
				for(int i=0;i<tds.size();i++){
					log.info(tds.elementAt(i).getText());
				}
		 } catch (Exception e) {
				e.printStackTrace();
		}
	      return data;
	  }
    public static Map<String,String> getCodeInfor(String li){
    	Map<String,String> data = new HashMap<String, String>();
        Parser parser = Parser.createParser(li, "gbk");	
        RegexFilter filter = new RegexFilter("[<a]*");
        try {
			NodeList tds = parser.extractAllNodesThatMatch(filter);
			Node a  = null ;
			String exchange = "" ;
			String code = "" ;
			String name = "" ;
			for(int i=0;i<tds.size();i++){
				try {
					a = tds.elementAt(i);
					if(a.getParent()!=null){
						String aHtml = a.getParent().toHtml();//<a target='_blank' href='http://quote.eastmoney.com/sz300447.html' >全信股份(300447)</a>
						List<String> list = match(aHtml.replace(">", " >"), "a","href");
						String href = list.get(0);//http://quote.eastmoney.com/sz300447.html
						String codeInfor = href.substring(
								href.lastIndexOf("/") + 1, href.length() - 5);
						exchange = codeInfor.substring(0, 2);
						code = codeInfor.substring(2, codeInfor.length());
					}
					String aTips = a.toHtml();//全信股份(300447)
					name = aTips.substring(0, aTips.lastIndexOf("("));
					if(code!=null&&!"".equals(code)&&!"null".equals(code)){
						data.put("name", name);
						data.put("code", code);
						data.put("exchange", exchange);
					}
					
				} catch (Exception e) {
//					e.printStackTrace();
					/*if(a!=null){
						log.info("###"+a.toHtml()+"处理异常！");
					}*/
				}
			}
		} catch (ParserException e) {
			e.printStackTrace();
		}
        return data;
    }
    public static String grabJsonDataFromURL(String urlStr){
    	URL url =null;  
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        String data = "";
		try {
			url = new URL(urlStr);
			conn = (HttpURLConnection)url.openConnection();
			 //设置超时间为3秒
	        conn.setConnectTimeout(3*1000);
	        conn.setRequestProperty("accept", "*/*"); 
	        conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//得到输入流
            inputStream = conn.getInputStream();  
            reader  = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            data = reader.readLine();
		} catch (Exception e) {
			log.info("Ocur exception ! url="+urlStr+",exc infor is "+e.toString());
		}  
    	return data;
    }
	public static void main(String[] args) {
		/*String str = "<li><a target=_blank href=http://quote.eastmoney.com/sz300498.html>温氏股份(300498)</a></li>";
		Map<String,String> data = getCodeInfor(str);
		String data0 ="var hq_str_sz300498=\"温氏股份,47.750,47.820,46.560,47.750,46.270,46.550,46.560,3578083,167478143.410,7300,46.550,17435,46.540,200,46.510,7300,46.500,400,46.480,900,46.560,1700,46.590,6200,46.600,2100,46.620,400,46.630,2015-12-24,11:35:55,00\"";
		log.info(data0.replace("\"", ""));
		String da = data0.replace("\"", "").split("=")[1];
		log.info(da);*/
		//grabCodeFromCFCenter();
		log.info(System.getProperty("user.dir")+"\\src\\main\\java\\com\\cyb\\qutoes\\utils");
		String str="易方达龙宝货币A(000789)";
		System.out.println(str.substring(0,str.indexOf("(")));
		System.out.println(str.substring(str.indexOf("(")+1,str.indexOf(")")));
	}
}
