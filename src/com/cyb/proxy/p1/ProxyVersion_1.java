package com.cyb.proxy.p1;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ProxyVersion_1 implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) throws Exception
    {    
        long start = System.currentTimeMillis();
        HelloWorld helloWorld = (HelloWorld)ProxyVersion_1.newProxyInstance(HelloWorld.class);
        System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
        helloWorld.print();
        System.out.println();
        
    }
    public static Object newProxyInstance(Class<?> interfaces) throws Exception
    {
        Method[] methods = interfaces.getMethods();
        
        StringBuilder sb = new StringBuilder(700);
        
        sb.append("package com.cyb.proxy.p1;\n\n");
        sb.append("public class StaticProxy_1 implements " +  interfaces.getSimpleName() + "\n");
        sb.append("{\n");
        sb.append("\t" + interfaces.getSimpleName() + " interfaces;\n\n");
        sb.append("\tpublic StaticProxy_1(" + interfaces.getSimpleName() +  " interfaces)\n");
        sb.append("\t{\n");
        sb.append("\t\tthis.interfaces = interfaces;\n");
        sb.append("\t}\n\n");
        for (Method m : methods)
        {
            sb.append("\tpublic " + m.getReturnType() + " " + m.getName() + "()\n");
            sb.append("\t{\n");
            sb.append("\t\tSystem.out.println(\"Before Hello World!\");\n");
            sb.append("\t\tinterfaces." + m.getName() + "();\n");
            sb.append("\t\tSystem.out.println(\"After Hello World!\");\n");
            sb.append("\t}\n");
        }
        sb.append("}");
        
        /** 生成一段Java代码 */
        String fileDir = System.getProperty("user.dir");
        String fileName = fileDir + "\\src\\com\\cyb\\proxy\\p1\\StaticProxy_1.java";
        File javaFile = new File(fileName);
        Writer writer = new FileWriter(javaFile);
        writer.write(sb.toString());
        writer.close();
        
        /** 动态编译这段Java代码,生成.class文件 */
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> iter = sjfm.getJavaFileObjects(fileName);
        CompilationTask ct = compiler.getTask(null, sjfm, null, null, null, iter);
        ct.call();
        sjfm.close();
        
        /** 将生成的.class文件载入内存，默认的ClassLoader只能载入CLASSPATH下的.class文件 */
        URL[] urls = new URL[] {(new URL("file:\\" + System.getProperty("user.dir") + "\\src"))};//build\\classes
        URLClassLoader ul = new URLClassLoader(urls);
        Class<?> c = ul.loadClass("com.cyb.proxy.p1.StaticProxy_1");
        
        /** 利用反射将c实例化出来 */
        Constructor<?> constructor = c.getConstructor(HelloWorld.class);
        
        HelloWorld helloWorldImpl = new HelloWorldImpl();
        Object obj = constructor.newInstance(helloWorldImpl);
        
        /** 使用完毕删除生成的代理.java文件和.class文件，这样就看不到动态生成的内容了 */
        /*File classFile = new File(fileDir + "\\src\\com\\xrq\\proxy\\StaticProxy.class");
        javaFile.delete();
        classFile.delete();*/
        
        return obj;
    }
}
