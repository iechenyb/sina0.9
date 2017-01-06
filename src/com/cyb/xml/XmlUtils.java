package com.cyb.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;


public class XmlUtils {
	public static  void generXml(String dest) throws Exception{
		// 第一种方式：创建文档，并创建根元�?
        // 创建文档:使用了一个Helper�?
        Document document = DocumentHelper.createDocument();

        // 创建根节点并添加进文�?
        Element root = DocumentHelper.createElement("students");
        document.setRootElement(root);
        OutputFormat format = new OutputFormat("    ", true);// 设置缩进�?4个空格，并且另起�?行为true
        XMLWriter xmlWriterFile = new XMLWriter(new FileWriter(dest), format);
        XMLWriter console = new XMLWriter();
        for(int i=0;i<5;i++){
	        // 第二种方�?:创建文档并设置文档的根元素节�?
	        Element student = DocumentHelper.createElement("student");
	        //Document child = DocumentHelper.createDocument(student);
	        // 添加属�??
	        student.addAttribute("id", "0000"+i);
	        // 添加子节�?:add之后就返回这个元�?
	        Element nameElement = student.addElement("name");
	        Element ageElement = student.addElement("age");
	        nameElement.setText("chenyb"+i);
	        ageElement.setText(String.valueOf(24+i));
	        root.add(student);
	        //document.add(student);
        }
        console.write(document);
        xmlWriterFile.write(document);
        xmlWriterFile.flush();
        System.out.println();
        /*XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream(dest), format);
        xmlWriter2.write(document);*/

	}
	public static void readXml(String src) throws ParserConfigurationException, DocumentException, SAXException, IOException{
		System.out.println("-----------SAXReader 解析 开始------------");
		SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(src));
        // 获取根元�?
        Element root = document.getRootElement();
        String rootName =  root.getName();
        System.out.println("Root: " +rootName);
        // 获取�?有子元素
        List<Element> childList = root.elements();
        //System.out.println("total child count: " + childList.size());
        // 获取特定名称的子元素
        List<Element> childList2 = root.elements("student");
        //System.out.println("hello child: " + childList2.size());

        // 获取名字为指定名称的第一个子元素
        Element firstWorldElement = root.element("student");
        // 输出其属�?
       /* System.out.println("first student Attr: "+ firstWorldElement.attribute(0).getName() + "="
                + firstWorldElement.attributeValue("id"));*/
        // 迭代输出
        for (Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element student = (Element) iter.next();
            showStudent( student );
        }
        System.out.println("-----------SAXReader 解析 结束------------");
        System.out.println("-----------DOMReader 解析 开始------------");
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // 注意要用完整类名
        org.w3c.dom.Document document2 = db.parse(new File(src));
        DOMReader domReader = new DOMReader();
        // 将JAXP的Document转换为dom4j的Document
        Document document3 = domReader.read(document2);
        Element root2 = document3.getRootElement();
        @SuppressWarnings("unchecked")
		List<Element> lst = root2.elements();
        for(int i=0;i<lst.size();i++){
        	Element student = lst.get(i);
        	showStudent( student );
        }
        System.out.println("-----------DOMReader 解析 结束------------");
	}
	public static void showStudent( Element e ){
		 System.out.println("student id="+e.attributeValue("id"));
		 @SuppressWarnings("unchecked")
		List<Element> childs = e.elements();
		 if(childs.size()>0){
			 for(int i=0;i<childs.size();i++){
				 String attName = childs.get(i).getName() ;
				 System.out.println("student Attr: " + attName+ "="+ childs.get(i).getText());
			 }
		 }
	}
	 public static void main(String[] args) throws Exception
    {
		 String basePath=System.getProperty("user.dir")+"/src/com/cyb/xml/";
		 XmlUtils.generXml(basePath+"stu.xml");//生成xml
		 XmlUtils.readXml(basePath+"stu.xml");//生成xml
    }
}
