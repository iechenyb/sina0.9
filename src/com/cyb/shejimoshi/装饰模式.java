package com.cyb.shejimoshi;
interface Sourceable {  
    public void method();  
}  
class Source1 implements Sourceable {  
	  
    @Override  
    public void method() {  
        System.out.println("the original method!");  
    }  
}  
class Decorator implements Sourceable {  
	  
    private Sourceable source;  
      
    public Decorator(Sourceable source){  
        super();  
        this.source = source;  
    }  
    @Override  
    public void method() {  
        System.out.println("before decorator!");  
        source.method();  
        System.out.println("after decorator!");  
    }  
}  
/**
 * Java中的I/O类库使用的就是装饰模式。
 * @author DHUser
 *
 */
public class 装饰模式 {
	public static void main(String[] args) {
		Sourceable obj = new Decorator(new Source1());//添加打印信息在方法执行前后  
		obj.method();
	}
	 /**
	  * 装饰器模式的应用场景：
		1、需要扩展一个类的功能。
		2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）
		缺点：产生过多相似的对象，不易排错！
		
		23种设计模式之一，英文叫DecoratorPattern，中文也叫装饰模式、修饰模式。
		装饰模式是在不改变类文件和不使用继承的情况下，运行期动态扩展一个对象的功能。
		原理是：增加一个修饰类包裹原来的类，包裹的方式一般是通过在将原来的对象作为修饰类的构造函数的参数。
		装饰类实现新的功能，但是，在不需要用到新功能的地方，它可以直接调用原来的类中的方法。
		修饰类必须和原来的类有相同的接口(没有接口可以直接继承自原来的类)。修饰模式是类继承的另外一种选择。
		类继承在编译时候增加行为，而装饰模式是在运行时增加行为。
	  */
	
}
