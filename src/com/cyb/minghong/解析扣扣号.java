package com.cyb.minghong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class 解析扣扣号 {
  static Map<String,String> hisQQs1 = new TreeMap<String,String>();
  static Map<String,String> newQQs = new TreeMap<String,String>();
  static Map<String, String> hisQQs = new TreeMap<String, String>(
          new Comparator<String>() {
              public int compare(String obj1, String obj2) {
                  // 降序排序
                  return obj2.compareTo(obj1);
              }
          });
  public static String getQQFromFile(String path){
	    //String path = System.getProperty("user.dir")+"/src/com/cyb/minghong/qq.txt";
		String pathTo = System.getProperty("user.dir")+"/"+date2long14(new Date())+".txt";
		readFileByLines(path);
		Iterator<String> iter = hisQQs.keySet().iterator();
		sortMap();
		while(iter.hasNext()){
			appendString2File1(iter.next(),pathTo);
		}
		return pathTo;
  }
  public static String initCompileQQ(String path1,String path2){
	    String addQQ = "";
	    hisQQs = new TreeMap<String,String>();
		newQQs = new TreeMap<String,String>();
		readFileByLines(path1,1);
		readFileByLines(path2,2);
		Iterator<String> iter2 = newQQs.keySet().iterator();
		while(iter2.hasNext()){
			String value=iter2.next();
			if(!hisQQs.containsKey(value)){
				addQQ = addQQ+value+",";
			}
		}
		return addQQ;
}
  public static void main(String[] args) {
	String path = System.getProperty("user.dir")+"/src/com/cyb/minghong/qq.txt";
	String pathTo = System.getProperty("user.dir")+"/src/com/cyb/minghong/"+date2long14(new Date())+".txt";
	//String newDatePath = System.getProperty("user.dir")+"/src/com/cyb/minghong/newlyDate.txt";
	readFileByLines(path);
	Iterator<String> iter = hisQQs.keySet().iterator();
	sortMap();
	while(iter.hasNext()){
		appendString2File1(iter.next(),pathTo);
	}
  }
  public static Long date2long14(Date date){
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
	   String dateString = formatter.format(date);
	   return Long.valueOf(dateString);
  }
  public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {  
	    Map<String, String> sortedMap = new LinkedHashMap<String, String>();  
	    if (oriMap != null && !oriMap.isEmpty()) {  
	        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(oriMap.entrySet());  
	        Collections.sort(entryList,  
	                new Comparator<Map.Entry<String, String>>() {  
	                    public int compare(Entry<String, String> entry1,  
	                            Entry<String, String> entry2) {  
	                        int value1 = 0, value2 = 0;  
	                        try {  
	                            value1 = Integer.valueOf(entry1.getValue());  
	                            value2 = Integer.valueOf(entry2.getValue());  
	                        } catch (NumberFormatException e) {  
	                            value1 = 0;  
	                            value2 = 0;  
	                        }  
	                        return value2 - value1;  
	                    }  
	                });  
	        Iterator<Map.Entry<String, String>> iter = entryList.iterator();  
	        Map.Entry<String, String> tmpEntry = null;  
	        while (iter.hasNext()) {  
	            tmpEntry = iter.next();  
	            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());  
	        }  
	    }  
	    return sortedMap;  
	}  
  public static StringBuffer readFileByLines(String path) {
	  	StringBuffer content = new StringBuffer("");
	      File file = new File(path);
	      BufferedReader reader = null;
	      try {
	          reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
	          String tempString = null;
	          int line = 1;
	          // 一次读入一行，直到读入null为文件结束
	          while ((tempString = reader.readLine()) != null) {
	        	  String str = tempString.trim();
	              // 显示行号
	              content.append(str+",");
	              if(str.startsWith("(")){
	            	  System.out.println(str.substring(1, str.length()-1));
	            	  String qq = str.substring(1, str.length()-1);
	            	  hisQQs.put(qq, qq);
	              }
	             // line++;
	          }
	          reader.close();
	      } catch (IOException e) {
	          e.printStackTrace();
	      } finally {
	          if (reader != null) {
	              try {
	                  reader.close();
	              } catch (IOException e1) {
	              }
	          }
	      }
	      return content;
	  }
  public static StringBuffer readFileByLines(String path,int type) {
  	StringBuffer content = new StringBuffer("");
      File file = new File(path);
      BufferedReader reader = null;
      try {
          reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
          String tempString = null;
          int line = 1;
          // 一次读入一行，直到读入null为文件结束
          while ((tempString = reader.readLine()) != null) {
        	  String str = tempString.trim();
              // 显示行号
              content.append(str+",");
        	  String qq = str;
        	  if(type==1){
        		  hisQQs.put(qq, qq);
        	  }else{
        		  newQQs.put(qq, qq);
        	  }
             // line++;
          }
          reader.close();
      } catch (IOException e) {
          e.printStackTrace();
      } finally {
          if (reader != null) {
              try {
                  reader.close();
              } catch (IOException e1) {
              }
          }
      }
      return content;
  }
  public static void appendString2File1(String content,String to){
  	FileWriter fw = null;
  	try {
	    	//如果文件存在，则追加内容；如果文件不存在，则创建文件
	    	File file = new File(to);
	    	fw = new FileWriter(file, true);
  	} catch (IOException e) {
  		e.printStackTrace();
  	}
  	PrintWriter pw = new PrintWriter(fw);
  	pw.println(content);//写一行
  	pw.flush();
  	try {
	    	fw.flush();
	    	pw.close();
	    	fw.close();
  	} catch (IOException e) {
  		e.printStackTrace();
  	}
  }
  public static void appendString2File3(String content,String to){
  	try {
	    		// 打开一个随机访问文件流，按读写方式
	    		RandomAccessFile randomFile = new RandomAccessFile(to, "rw");
	    		// 文件长度，字节数
	    		long fileLength = randomFile.length();
	    		// 将写文件指针移到文件尾。
	    		randomFile.seek(fileLength);
	    		//String s=new String(content.getBytes("gb2312"), "utf-8");//编码转换    
	    		randomFile.writeChars(content+"\n");
	    		randomFile.close();
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  }
  public static void sortMap(){
	  List<Map.Entry<String, String>> infoIds =
			    new ArrayList<Map.Entry<String, String>>(hisQQs.entrySet());

			//排序前
			for (int i = 0; i < infoIds.size(); i++) {
			    String id = infoIds.get(i).toString();
			    System.out.println(id);
			}
			//d 2
			//c 1
			//b 1
			//a 3

			//排序
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {   
			    public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {      
			        //return (o2.getValue() - o1.getValue()); 
			        return (o1.getKey()).toString().compareTo(o2.getKey());
			    }
			}); 

			//排序后
			for (int i = 0; i < infoIds.size(); i++) {
			    String id = infoIds.get(i).toString();
			    System.out.println(id);
			}
  }
}
