package com.cyb.url;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.cyb.file.FileUtils;

public class SpiderHttpClient  {
	static String charset = "gbk";//"gb2312"
	static String filepath = "d:/SpiderHttpClient.htm";
	static String url_str = "http://www.czce.com.cn/portal/DFSStaticFiles/Future/2016/20160215/FutureDataWhsheet.htm";
    public static void main(String[] args) throws Exception {
        HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(url_str);
        HttpResponse response = hc.execute(hg);
        HttpEntity entity = response.getEntity();
        InputStream htm_in = null;        
        if(entity != null){
            System.out.println("文件大小："+entity.getContentLength());
            htm_in = entity.getContent();
            StringBuffer htm_str = InputStream2String(htm_in,charset);
            FileUtils.overideString2File(htm_str.toString(),filepath);
//            saveStr2Html(filepath,htm_str.toString());
            System.out.println("文件下载成功！"+filepath);
        }
    }
    public static void downLoadContent(String url,String toDir){
    	 try {
    		 System.out.println(url);
			HttpClient hc = new DefaultHttpClient();
			 HttpGet hg = new HttpGet(url);
			 HttpResponse response = hc.execute(hg);
			 HttpEntity entity = response.getEntity();
			 InputStream htm_in = null;
			 if(entity != null){
			     System.out.println("文件大小："+entity.getContentLength());
			     htm_in = entity.getContent();
			     StringBuffer htm_str = InputStream2String(htm_in,charset);
			     FileUtils.overideString2File(htm_str.toString(),toDir);
			     System.out.println("文件下载成功！"+toDir);
			 }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * Method: saveHtml 
     * Description: save String to file
     * @param filepath
     * file path which need to be saved
     * @param str
     * string saved
     */
    public static void saveStr2Html(String filepath, String str){        
        try {
            /*@SuppressWarnings("resource")
            FileWriter fw = new FileWriter(filepath);
            fw.write(str);
            fw.flush();*/
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true));
            outs.write(str);
            outs.close();
        } catch (IOException e) {
            System.out.println("Error at save html...");
            e.printStackTrace();
        }
    }
    
    
    /**
     * Method: InputStream2String 
     * Description: make InputStream to String
     * @param in_st
     * inputstream which need to be converted
     * @param charset
     * encoder of value
     * @throws IOException
     * if an error occurred 
     */
    public static StringBuffer InputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
        	System.out.println(line);
            res.append(line+"\n");
        }
        return res;
    }
}
