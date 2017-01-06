package com.cyb.shejimoshi;
abstract class tst { 
	public void x(){};
	public void y(){};
}
abstract class AbstractCalculator1 {  
    /*主方法，实现对本类其它方法的调用*/  
    public final int calculate(String exp,String opt){  
        int array[] = split(exp,opt);  
        return calculate(array[0],array[1]);  
    }  
    /*被子类重写的方法*/  
    abstract public int calculate(int num1,int num2);  
    public int[] split(String exp,String opt){  
        String array[] = exp.split(opt);  
        int arrayInt[] = new int[2];  
        arrayInt[0] = Integer.parseInt(array[0]);  
        arrayInt[1] = Integer.parseInt(array[1]);  
        return arrayInt;  
    }  
}
class Plus1 extends AbstractCalculator1 {  
    @Override  
    public int calculate(int num1,int num2) {  
        return num1 + num2;  
    }  
} 
class Mins1 extends AbstractCalculator1 {  
    @Override  
    public int calculate(int num1,int num2) {  
        return num1 - num2;  
    }  
} 
public class 模板模式 {
	public static void main(String[] args) {  
        String exp = "8+8";  
        AbstractCalculator1 cal = new Plus1();  
        int result = cal.calculate(exp, "\\+");  
        System.out.println(result);  
        String exp1 = "8-2";  
        AbstractCalculator1 min = new Mins1();  
        int result1 = min.calculate(exp1, "\\-");  
        System.out.println(result1);  
    }  
}
