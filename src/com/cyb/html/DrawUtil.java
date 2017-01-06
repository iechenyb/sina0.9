package com.cyb.html;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DrawUtil {
	static Logger log = LoggerFactory.getLogger(DrawUtil.class);
	public static String grabJsonDataFromURL(String urlStr){
    	URL url =null;  
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        String data = "";
		try {
			url = new URL(urlStr);  
			conn = (HttpURLConnection)url.openConnection();
			 //设置超时间为3�?
	        conn.setConnectTimeout(3*1000);
	        conn.setRequestProperty("accept", "*/*"); 
	        conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			//得到输入�?
            inputStream = conn.getInputStream();  
            reader  = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
            data = reader.readLine();
		} catch (Exception e) {
			log.info("Ocur exception ! url="+urlStr+",exc infor is "+e.toString());
		}  
		log.info("Draw data from "+url+" success!\nData is "+data);
    	return data;
    }
}
