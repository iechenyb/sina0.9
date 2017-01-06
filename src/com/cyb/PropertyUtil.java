package com.cyb;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class PropertyUtil {
	private static Properties p = null;
	private static Properties p1 = null;
	private static ResourceBundle rb = null;
	static Log log = LogFactory.getLog(PropertyUtil.class);
	public synchronized static void initByName() throws Exception {
		if(rb==null){
			rb = ResourceBundle.getBundle("app");//放到classe根目录下边才能找到
		}
	}
	public synchronized static void initByPath() throws Exception {
		InputStream inputstream = null;
		try {
			if (p == null) 
			{
				String filePath = System.getProperty("user.dir")+"/src/com/cyb/property/config.properties";
				p = new Properties();
				log.info("初始化属性文件:"+filePath);
				inputstream = new FileInputStream(filePath);
				p.load(inputstream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(inputstream!=null){
				inputstream.close();
				inputstream = null;
			}
		}
	}
	public synchronized static void initByPackage() throws Exception {
		InputStream inputstream = null;
		try {
			if (p1 == null) 
			{
				String filePath = "com/cyb/property/config.properties"; //不能加*/src/*
				p1 = new Properties();
				inputstream = PropertyUtil.class.getClassLoader().getResourceAsStream(filePath);
				log.info("初始化属性文件:"+filePath);
				p1.load(inputstream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(inputstream!=null){
				inputstream.close();
				inputstream = null;
			}
		}
	}

	public static String get(String key) {
		String result = "";
		try {
			result = p.getProperty(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String get1(String key) {
		String result = "";
		try {
			result = p1.getProperty(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static String get2(String key) {
		String result = "";
		try {
			result =rb.getString(key);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public static void main(String[] s) {
		 try {
			PropertyUtil.initByPackage();
			log.info(PropertyUtil.get1("name"));
			PropertyUtil.initByPath();
			log.info(PropertyUtil.get("name"));
			PropertyUtil.initByName();
			log.info(PropertyUtil.get("name"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}