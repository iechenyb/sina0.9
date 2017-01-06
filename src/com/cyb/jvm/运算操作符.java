package com.cyb.jvm;

public class 运算操作符 {
public static void main(String[] args) {
    System.out.println(1==1||2==2);//true
    System.out.println(1==1|2==2);//true
    System.out.println(false|false);//false
    System.out.println(false||false);//false
    System.out.println(true|false);//true
    System.out.println(true||false);//true
    System.out.println(3|2);//3
    System.out.println(1|1);//1
    System.out.println(13&12);//12
    System.out.println(1&1);//1
    System.out.println(12>>1);//6
    System.out.println(12<<1);//24无符号
    System.out.println(-12>>1);//-6
    System.out.println(-12<<1);//-24
    
    System.out.println(12>>>1);//
    //System.out.println(12<<<1);//无符号
    System.out.println(-12>>>1);//
    //System.out.println(-12<<<1);//
    
    
}
}
