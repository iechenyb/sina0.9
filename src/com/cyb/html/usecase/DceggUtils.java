package com.cyb.html.usecase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.NodeVisitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.cyb.dom.Html2DomUtils;
import com.cyb.file.FileUtils;
import com.cyb.url.UrlUtils;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;


public class DceggUtils {
	public static Map tag=null;
	public static List<Map<String,Object>> ggs = new ArrayList<Map<String,Object>>();
	public static List<String> dates = new ArrayList<String>();
	public static void main(String[] args) {
		String dcexggPage = "http://www.dce.com.cn/dalianshangpin/yw/fw/jystz/ywtz/13305-1.html";
		NodeFilter filter = null;
		filter = new TagNameFilter("ul");
		try {
			/*UrlUtils.downLoadFromUrl(dcexggPage, "tmp.html", "d:/");
			domAna();*/
			anaString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*String data = JSONArray.fromObject(ggs).toString();
		FileUtils.overideString2File(data, "d:/dcexggs.txt");*/
	}
	public static void anaString() throws IOException, ParserException{
		String dcexggPage = "http://www.dce.com.cn/dalianshangpin/yw/fw/jystz/ywtz/13305-1.html";
		BufferedReader reader = new BufferedReader(new InputStreamReader(UrlUtils.getStream(dcexggPage),"utf-8"));
		String content = "";
		StringBuffer html = new StringBuffer("");
		boolean begin = false;
		int num =0;
		while((content = reader.readLine())!=null){
			if(content.contains("list_tpye06")){
				begin=true;
				num=1;
				html.append(content+"\n");
			}
			if(content.contains("<li>")&&begin){
				html.append(content+"\n");
			}
			if(content.contains("</ul>")&&num==1){
				begin = false;
				html.append(content+"\n");
				break;
			}
		}
		//System.out.println(html.toString());
		try {
			String shtml = Html2DomUtils.genStandardHtml("d:/tmp.html", html.toString());
			//domAna();
			ana(shtml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void domAna() throws SAXException, IOException{
		DOMParser parser = new DOMParser();    
        //解析HTML文件  
        parser.parse("d:/tmp.html");  //必须有html，body标签
        //获取解析后的DOM树    
        Document document = parser.getDocument();    
        //通过getElementsByTagName获取Node    
        org.w3c.dom.NodeList nodeList = document.getElementsByTagName("li");
        Map<String,Object> map = null;
        for (int i = 0; i < nodeList.getLength(); i++) {
        	map = new HashMap<String,Object>();
            Element e = (Element)nodeList.item(i);    
            Node node = e.getChildNodes().item(1);
            map.put("date", e.getChildNodes().item(0).getTextContent());
            map.put("title", node.getAttributes().getNamedItem("title").getNodeValue());
            map.put("ggurl", "http://www.dce.com.cn"+node.getAttributes().getNamedItem("href").getNodeValue());
            map.put("content",getContent(map.get("ggurl").toString()));
            ggs.add(map);
        }  
        String data = JSONArray.fromObject(ggs).toString();
		FileUtils.overideString2File(data, "d:/dcexggs.txt");
        File file = new File("d:/tmp.html");
        if(file.exists()){
        	file.delete();
        }
	}
	
	public static String getContent(String url) throws IOException{
		    BufferedReader reader = new BufferedReader(new InputStreamReader(UrlUtils.getStream(url),"utf-8"));
			String content = "";
			StringBuffer html = new StringBuffer("");
			boolean begin = false;
			int num =0;
			while((content = reader.readLine())!=null){
				//System.out.println(content);
				if(content.contains("detail_content")){
					num=num+1;
					if(num==2){
						begin=true;
					}
				}
				
				if(begin&&num==2){
					html.append(content+"\n");
				}
				/*if(content.contains("</div>")&&num==1){
					begin = false;
					html.append(content+"\n");
					break;
				}*/
			}
			html.append("</div>\n");
			//System.out.println(html.toString());
			/*try {
				Html2DomUtils.genStandardHtml("d:/tmp.html", html.toString());
				domAna();
			} catch (SAXException e) {
				e.printStackTrace();
			}*/
			return html.toString();
	}
	 public static void ana(String re) throws ParserException{
		 Parser parser = new Parser (re);
		 NodeFilter filter = new HasAttributeFilter("li");
		 NodeList  lis = parser.extractAllNodesThatMatch(filter);
		  Map<String,Object> map = null;
		 for(int i=0;i<lis.size();i++){
			 map = new HashMap<String,Object>();
			 String date = lis.elementAt(i).getChildren().elementAt(0).getChildren().elementAt(0).toHtml();	
			 map.put("date",date);
			 String a= lis.elementAt(i).getChildren().elementAt(1).toHtml();
			 map.putAll(anaNode(a));
			 ggs.add(map);
		 }
		 String data = JSONArray.fromObject(ggs).toString();
		 FileUtils.overideString2File(data, "d:/dcexggs.txt");
	 }
	@SuppressWarnings("unchecked")
	public static Map<String,String> anaNode(String resource){
		 try{
			  Parser parser = new Parser (resource);
			  NodeVisitor visitor = new NodeVisitor(true, true) {
	                @SuppressWarnings("unchecked")
					public void visitTag(Tag tag) {
	                   if(tag.getTagName().equals("A")){
	                	   DceggUtils.tag = new HashMap<String,String>();
		                   DceggUtils.tag.put("title", tag.getAttribute("title"));
		                   DceggUtils.tag.put("href", tag.getAttribute("href"));
		                   System.out.println(DceggUtils.tag);
	                   }
	                }
	          };
	          parser.visitAllNodesWith(visitor);
	        }catch( Exception e ) {     
	            e.printStackTrace();
	        }
		 return  DceggUtils.tag;
	}
}
