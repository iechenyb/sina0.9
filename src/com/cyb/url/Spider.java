package com.cyb.url;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
//http://www.cnblogs.com/ywl925/archive/2013/08/20/3270875.html
public class Spider {
	static String charset = "gbk";//"gb2312"
	static String filepath = "d:/Spider.htm";
	static String url_str = "http://www.czce.com.cn/portal/DFSStaticFiles/Future/2016/20160215/FutureDataWhsheet.htm";
    public static void main(String[] args) {
        URL url = null;
        try {
            url = new URL(url_str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        int sec_cont = 1000;
        try {
            URLConnection url_con = url.openConnection();
            url_con.setDoOutput(true);
            url_con.setReadTimeout(10 * sec_cont);
            url_con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            InputStream htm_in = url_con.getInputStream();
            String htm_str = InputStream2String(htm_in,charset);
            saveHtml(filepath,htm_str);
            
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
    public static void saveHtml(String filepath, String str){
        
        try {
            /*@SuppressWarnings("resource")
            FileWriter fw = new FileWriter(filepath);
            fw.write(str);
            fw.flush();*/
            OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(filepath, true), "utf-8");
            outs.write(str);
            System.out.print(str);
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
    public static String InputStream2String(InputStream in_st,String charset) throws IOException{
        BufferedReader buff = new BufferedReader(new InputStreamReader(in_st, charset));
        StringBuffer res = new StringBuffer();
        String line = "";
        while((line = buff.readLine()) != null){
            res.append(line+"\n");
        }
        return res.toString();
    }

}