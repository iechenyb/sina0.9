package com.cyb.jvm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.cyb.Person;
import com.cyb.file.FileUtils;

public class ClassLoaderUtils extends ClassLoader{  
	public ClassLoaderUtils(){
		
	}
    // 获取二进制字节流  
    private byte[] getData(InputStream in){  
        byte[] data = null;  
        try {  
            data = new byte[in.available()];  
            in.read(data);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                if(in != null){  
                    in.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return data;  
    }  
      
    // 获得文件流  
    private InputStream getInputStream(String clsPath){  
         try {
			return new  FileInputStream( new  File(clsPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
    }  
    
    // 获得文件流  
    private InputStream getInputStreamPackage(String packageName){  
    	packageName = "/"+packageName.replace(".", "/")+".class";  
        return getClass().getClassLoader().getResourceAsStream(packageName);  //"com/cyb/clsName.class"
    }  
      
    protected Class<?> findClass(String path,String name) throws ClassNotFoundException {  
        InputStream in = getInputStream(path);  
        if(in == null){  
            System.out.println("自定义加载失败！");  
            return super.loadClass(name);  
        }  
        byte[] data = this.getData(in);    
        return super.defineClass(name,data, 0, data.length);  
    }  
    
    // 这个源码是直接抛出异常，并且是protected，好让我们重写findClass  
    @Override  
    protected Class<?> findClass(String name) throws ClassNotFoundException {  
        InputStream in = getInputStreamPackage(name);  
        if(in == null){  
            System.out.println("自定义加载失败！");  
            return super.loadClass(name);  
        }  
        byte[] data = this.getData(in);    
        return super.defineClass(name,data, 0, data.length);  
    }  
    //从txt中读出java代码，写入java文件，编译执行测试
   static String packageName = "com.cyb.jvm.classloader";//已知包路径
   static String clsName = "IEChenyb";
   static String src = System.getProperty("user.dir")+"/ChenybPerson.txt";//源文档名称可以任意，存储位置任意
   static String base="/haha/";
   static String path = System.getProperty("user.dir")+base+packageName.replace(".", "/");
   static String toJavaFile = path+File.separator+clsName+".java";//生成的java类名必须和代码的public class类名相同
   static String toClassFile = path+File.separator+clsName+".class";
   static String packageBasePath = System.getProperty("user.dir")+base;//去掉path的包结构的路径，可以是任意的路径  
    //  测试  
    public static void main(String[] args) throws Exception,   
        IllegalAccessException, ClassNotFoundException {  
        genJava();
        compileJave();
        load1();
        //load1_1();//不在系统自定义目录，所以执行会报错，这里class生产的目录是自定义的任意目录
        load2();
        load3();
        load4();
        //javaFile.delete();
    }  
    public static void genJava() throws IOException{
    	System.out.println("------------genJava-------------");
    	File  javaFile = new File(toJavaFile);
        System.out.println(toJavaFile);
        if(javaFile.exists()){
        	if(javaFile.getParentFile()==null){
        		javaFile.getParentFile().mkdirs();
        	}
        	javaFile.delete();
        	javaFile.createNewFile();
        }else{
        	File parent = javaFile.getParentFile();
        	if(!parent.exists()){
        		javaFile.getParentFile().mkdirs();
        	}
        	javaFile.createNewFile();
        }
        FileUtils.copyFile(src, toJavaFile);
    }
    public static void compileJave() throws IOException{
    	System.out.println("-------------compileJave------------");
    	/** 动态编译这段Java代码,生成.class文件 */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(toJavaFile);
        CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();
        System.out.println(toClassFile);
    }
    public static void load1() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
    	//通过class的文件流加载
    	System.out.println("------------load1-------------"); 
    	ClassLoaderUtils cu = new ClassLoaderUtils(); //从默认的classpath下查找
	    Chenyb cyb_ = (Chenyb) cu.findClass(toClassFile,packageName+"."+clsName).newInstance(); 
	    cyb_.print(null);
    }
    public static void load1_1() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
    	//通过class的文件流加载
    	System.out.println("------------load1_1-------------"); 
    	ClassLoaderUtils cu = new ClassLoaderUtils(); //从默认的classpath下查找
	    Chenyb cyb_ = (Chenyb) cu.findClass(packageName+"."+clsName).newInstance(); 
	    cyb_.print(null);
    }
    public static void load2() throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
    	//通过包名加载
    	System.out.println("-----------load2--------------");
    	Chenyb cyb = null;  
    	Class<?> c = null;
        //自定义目录加载
        /** 将生成的.class文件载入内存，默认的ClassLoader只能载入CLASSPATH下的.class文件,跟java文件目录相同 */
        URL[] urls = new URL[] {(new URL("file:\\" + packageBasePath))};
		@SuppressWarnings("resource")
		URLClassLoader ul = new URLClassLoader(urls);
        c = ul.loadClass(packageName+"."+clsName);
        /** 利用反射将c实例化出来 */
        Constructor<?> constructor = c.getConstructor(String.class);
        Object obj = constructor.newInstance("heihei!");
        cyb  = (Chenyb) obj;
        cyb.print(null);
   }
    
    public static void load3() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    	System.out.println("------------load3-------------"); 
    	//从系统的classes目录加载
    	URL url = new URL("file:/"+System.getProperty("user.dir")+"\\build\\"); 
	    @SuppressWarnings("resource")
	    ClassLoader urlloader = new URLClassLoader(new URL[]{url}); 
	    Class<?> c = urlloader.loadClass("com.cyb.Person");
    	Person person = (Person) c.newInstance(); 
		person.setAge("200");
		System.out.println("my class person getage="+person.getAge());
    }
    public static void load4() throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
    	System.out.println("------------load4-------------");
    	ClassLoaderUtils myLoader = new ClassLoaderUtils();
        Object p1 =  myLoader.findClass("com.cyb.Person").newInstance();
        System.out.println(p1);
        Person p = (Person)p1;
        System.out.println("person.age="+p.getAge()); 
        
    }
}  
