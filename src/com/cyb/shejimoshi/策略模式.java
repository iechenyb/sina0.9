package com.cyb.shejimoshi;
interface ICalculator {  
    public int calculate(String exp);  
}
abstract class AbstractCalculator {  
    public int[] split(String exp,String opt){  
        String array[] = exp.split(opt);  
        int arrayInt[] = new int[2];  
        arrayInt[0] = Integer.parseInt(array[0]);  
        arrayInt[1] = Integer.parseInt(array[1]);  
        return arrayInt;  
    }  
}  
class Plus extends AbstractCalculator implements ICalculator {  
    @Override  
    public int calculate(String exp) {  
        int arrayInt[] = split(exp,"\\+");  
        return arrayInt[0]+arrayInt[1];  
    }  
} 
class Minus extends AbstractCalculator implements ICalculator {  
    @Override  
    public int calculate(String exp) {  
        int arrayInt[] = split(exp,"-");  
        return arrayInt[0]-arrayInt[1];  
    }  
} 
/**
 * 
优点
1、   提供了一种替代继承的方法，而且既保持了继承的优点(代码重用)，还比继承更灵活(算法独立，可以任意扩展)；

2、   避免程序中使用多重条件转移语句，使系统更灵活，并易于扩展；

3、   遵守大部分GRASP原则和常用设计原则，高内聚、低偶合；

4、   易于进行单元测试，各个算法区分开，可以针对每个算法进行单元测试；

5、   ……

缺点
1、   因为每个具体策略类都会产生一个新类，所以会增加系统需要维护的类的数量；

2、   选择何种算法需要客户端来创建对象，增加了耦合，这里可以通过与工厂模式结合解决该问题；

3、   程序复杂化。
 * @author DHUser
 *
 */
class Multiply extends AbstractCalculator implements ICalculator {  
    @Override  
    public int calculate(String exp) {  
        int arrayInt[] = split(exp,"\\*");  
        return arrayInt[0]*arrayInt[1];  
    }  
}
public class 策略模式 {
 public static void main(String[] args) {
	 String exp = "2+8";  
     ICalculator add = new Plus();  
     int result = add.calculate(exp);  
     System.out.println(result);  
     String exp1 = "2-8";  
     ICalculator min = new Minus();  
     int result1 = min.calculate(exp1);  
     System.out.println(result1);  
}
}
