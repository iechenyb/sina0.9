package com.cyb.shejimoshi;
interface Sourceableqj {  
    public void method();  
}
class SourceSub1 implements Sourceableqj {  
    @Override  
    public void method() {  
        System.out.println("this is the first sub!");  
    }  
} 
class SourceSub2 implements Sourceableqj {  
    @Override  
    public void method() {  
        System.out.println("this is the second sub!");  
    }  
} 
abstract class Bridge {  
    private Sourceableqj source;  
    public void method(){  
        source.method();  
    }  
    public Sourceableqj getSource() {  
        return source;  
    }  
    public void setSource(Sourceableqj source) {  
        this.source = source;  
    }  
}
class MyBridge extends Bridge {  
    public void method(){  
        getSource().method();  
    }  
}  
public class 桥接模式 {
 public static void main(String[] args) {
	  Bridge bridge = new MyBridge();  
      
      /*调用第一个对象*/  
      Sourceableqj source1 = new SourceSub1();  
      bridge.setSource(source1);  
      bridge.method();  
        
      /*调用第二个对象*/  
      Sourceableqj source2 = new SourceSub2();  
      bridge.setSource(source2);  
      bridge.method();  
}
}
