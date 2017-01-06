package com.cyb.jvm;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import com.cyb.Contanst;
import com.cyb.Person;
import com.cyb.json.JsonUtil;
public class 装载Class extends ClassLoader{
	public static String clsPath =  null;
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(System.getenv());
		System.out.println(System.getProperties());
		System.out.println("path1:"+System.getProperty("sun.boot.class.path"));
		System.out.println("path2:"+System.getProperty("java.ext.dirs"));
		System.out.println("path3:"+System.getProperty("java.class.path"));// 查看当前class生成目录和依赖包所在目录
		clsPath = System.getProperty("java.class.path").split(";")[0];
		Class<?> c;
		ClassLoader cl;
		cl = ClassLoader.getSystemClassLoader();
		System.out.println(cl);
		while (cl != null) {
			cl = cl.getParent();
			System.out.println(cl);
		}
		try {
			c = Class.forName("java.lang.Object");
			cl = c.getClassLoader();
			System.out.println("java.lang.Object's loader is " + cl);
			c = Class.forName("com.cyb.json.JsonUtil");
			System.out.println("JsonUtil's loader is " + cl);
			cl = c.getClassLoader();
			JsonUtil jsonUtil = (JsonUtil) c.newInstance();
			jsonUtil.test();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		  try { 
			  /**
			   * 自定目录，并通过类加载器加载任意目录或者多个目录中的class文件
			   */
			  URL url = new URL("file:/"+System.getProperty("user.dir")+"/mycls"); //clsPath 默认的class路径
			  @SuppressWarnings("resource")
			  ClassLoader myloader1 = new URLClassLoader(new URL[]{url}); 
			  Class<?> c3 =myloader1.loadClass("com.cyb.Contanst");
			  System.out.println("自定义目录加载指定的class:"+c3); 
			  Contanst person = (Contanst) c3.newInstance(); 
			  System.out.println("my class  Contanst name="+person.name);
		  } catch (Exception e1) { e1.printStackTrace(); }
		 	
		  try { 
			    装载Class myLoader = new 装载Class(); 
			  Class<? extends 装载Class> c2 = myLoader.getClass();
			  ClassLoader loader = c2.getClassLoader(); 
			  System.out.println(loader);
			  System.out.println(loader.getParent());
			  System.out.println(loader.getParent().getParent()); 
			  /**
			   * 我们可以判定系统类加载器的父加载器是标准扩展类加载器，
			   * 但是我们试图获取标准扩展类加载器的父类加载器时确得到了null，
			   * 就是说标准扩展类加载器本身强制设定父类加载器为null。
			   */
			 Class p =  myLoader.findClass("Contanst");
		  } catch(Exception e) {
			  e.printStackTrace(); 
		 }		 
	}
	@SuppressWarnings("resource")
	public  static  byte [] loadClassData(String name)    {
        FileInputStream fis  =   null ;
         byte [] data  =   null ;
         try  {
            fis  = new  FileInputStream( new  File(clsPath+"/com/cyb/"+name+".class"));
            ByteArrayOutputStream baos  =   new  ByteArrayOutputStream();
             int  ch  =   0 ;
             while  ((ch  =  fis.read())  !=   - 1 )  {
                baos.write(ch);             
            }
            data  =  baos.toByteArray();
        }  catch  (IOException e)  {
            e.printStackTrace();
        }      
         System.out.println("对象字节大小："+data.length);
         return  data;
    } 

 
	public   Class<?> findClass(String name)    {
        byte [] data  =  loadClassData(name);
        return  defineClass(name, data,  0 , data.length);
   }
}
