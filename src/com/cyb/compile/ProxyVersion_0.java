package com.cyb.compile;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ProxyVersion_0 implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) throws Exception
    {    
        long start = System.currentTimeMillis();
        HelloWorld helloWorld = (HelloWorld)ProxyVersion_0.newProxyInstance();
        System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
        helloWorld.print();
        System.out.println();        
    }
    public static Object newProxyInstance() throws Exception
    {
        String src = "package com.cyb.compile;\n\n" + 
                     "public class StaticProxy implements HelloWorld\n" + 
                     "{\n" + 
                     "\tHelloWorld helloWorld;\n\n" + 
                     "\tpublic StaticProxy(HelloWorld helloWorld)\n" + 
                     "\t{\n" + 
                     "\t\tthis.helloWorld = helloWorld;\n" + 
                     "\t}\n\n" + 
                     "\tpublic void print()\n" + 
                     "\t{\n" + 
                     "\t\tSystem.out.println(\"Before Hello World!\");\n" + 
                     "\t\thelloWorld.print();\n" + 
                     "\t\tSystem.out.println(\"After Hello World!\");\n" + 
                     "\t}\n" + 
                     "}";
        
        /** 生成一段Java代码 */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + "\\src\\com\\cyb\\compile\\StaticProxy.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(src);
        writer.close();
        
        /** 动态编译这段Java代码,生成.class文件 */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
        CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();
        
        /** 将生成的.class文件载入内存，默认的ClassLoader只能载入CLASSPATH下的.class文件 */
        URL[] urls = new URL[] {(new URL("file:\\" + System.getProperty("user.dir") + "\\src"))};
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> c = ul.loadClass("com.cyb.compile.StaticProxy");
        
        /** 利用反射将c实例化出来 */
        Constructor<?> constructor = c.getConstructor(HelloWorld.class);
        HelloWorld helloWorldImpl = new HelloWorldImpl();
        HelloWorld helloWorld = (HelloWorld)constructor.newInstance(helloWorldImpl);
        
        /** 使用完毕删除生成的代理.java文件和.class文件，这样就看不到动态生成的内容了 */
        File classFile = new File(fileDir + "\\src\\com\\cyb\\compile\\StaticProxy.class");
        javaFile.delete();
        classFile.delete();
        
        return helloWorld;
    }
}
