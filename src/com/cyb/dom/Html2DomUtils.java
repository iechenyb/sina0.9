package com.cyb.dom;

import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cyb.file.FileUtils;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class Html2DomUtils {
	 static public void main(String[] args) throws SAXException, IOException{  
		 System.out.println(System.getProperty("user.dir"));
	        //创建一个解析器    
	        DOMParser parser = new DOMParser();    
	        //解析HTML文件  
	        parser.parse("src/com/cyb/dom/tmp.html");  //必须有html，body标签
	        //获取解析后的DOM树    
	        Document document = parser.getDocument();    
	            
	        //通过getElementsByTagName获取Node    
	        NodeList nodeList = document.getElementsByTagName("div");
	        for (int i = 0; i < nodeList.getLength(); i++) {    
	            Element e = (Element)nodeList.item(i);    
	            //System.out.print(e.getAttribute("href") + "\t");    
	            System.out.println(e.getTextContent());    
	        }  
	        
	       /* Element e = document.getElementById("tt");
	        System.out.println(e);*/
	    }  
	 public static String  genStandardHtml(String dest,String content){
		 StringBuffer html = new StringBuffer("");
		 html.append("<html>\n");
		 html.append("<body>\n") ; 
		 html.append(content);
		 html.append("</body>\n") ;   
		 html.append("</html>\n");    
		 FileUtils.overideString2File(html.toString(), dest);
		 return html.toString();
	 }
	 
	 public void jsoup(){
		/* Document doc = Jsoup.parse(input, "UTF-8", "http://www.dangdang.com");
		 Element content = doc.getElementById("content");
		 Elements links = content.getElementsByTag("a");
		 for (Element link : links) {
		   String linkHref = link.attr("href");
		   String linkText = link.text();
		 }*/
	 }
}
