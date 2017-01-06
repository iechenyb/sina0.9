package com.cyb.shejimoshi;
interface Targetable {  
    /* 与原类中的方法相同 */  
    public void method1();    
    /* 新类的方法 */  
    public void method2();  
} 
class Source {  
    public void method1() {  
        System.out.println("this is original method1!");  
    }  
    public void method2() {  
        System.out.println("this is original method2!");  
    } 
}  
class Adapter extends Source implements Targetable {  
	  
    @Override  
    public void method2() {  
        System.out.println("this is the targetable method!");  
    }  
}  
//基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，而是持有Source类的实例，以达到解决兼容性的问题
public class 适配器模式 {
	public static void main(String[] args) {  
        Targetable target = new Adapter();  
        target.method1();  
        target.method2();  
    }  
}
