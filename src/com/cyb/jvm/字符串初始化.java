package com.cyb.jvm;

public class 字符串初始化 {
 public static void main(String[] args) {
	//代码1  
	 String sa = "ab";                                          
	 String sb = "cd";                                       
	 String sab=sa+sb;                                      
	 String s="abcd";  
	 System.out.println(sab==s); // false  
	 //代码2  
	 String sc="ab"+"cd";  
	 String sd="abcd";  
	 System.out.println(sc==sd); //true  
	 
	//代码1  
	 String sa1=new String("Hello world");            
	 String sb1=new String("Hello world");      
	 System.out.println(sa1==sb1);  // false       
	 //代码2    
	 String sc1="Hello world";    
	 String sd1="Hello world";  
	 System.out.println(sc1==sd1);  // true  
	 /**
	  *   代码1中局部变量sa1,sb1中存储的是JVM在堆中new出来的两个String对象的内存地址。
	  *   虽然这两个String对象的值(char[]存放的字符序列)都是"Hello world"。 因此"=="比较的是两个不同的堆地址。
	  *   代码2中局部变量sc1,sd1中存储的也是地址，但却都是常量池中"Hello world"指向的堆的唯一的那个拘留字符串对象的地址 。自然相等了。
	  */
	 String ssab = "chenyb".intern();
	 String ssac = "chenyb".intern();
	 String ssad = new String("chenyb").intern();
	 String ssae = new String("chenyb");
	 System.out.println(ssab == ssac);
	 System.out.println(ssac == ssad);
	 System.out.println(ssac == ssae);//false
}
 /**
  *    代码1中局部变量sa,sb存储的是堆中两个拘留字符串对象的地址。而当执行sa+sb时，
  *    JVM首先会在堆中创建一个StringBuilder类，同时用sa指向的拘留字符串对象完成初始化，
  *    然后调用append方法完成对sb所指向的拘留字符串的合并操作，接着调用StringBuilder的toString()方法在堆中创建一个String对象，
  *    最后将刚生成的String对象的堆地址存放在局部变量sab中。而局部变量s存储的是常量池中"abcd"所对应的拘留字符串对象的地址。 
  *    sab与s地址当然不一样了。这里要注意了，代码1的堆中实际上有五个字符串对象：三个拘留字符串对象、一个String对象和一个StringBuilder对象。
                代码2中"ab"+"cd"会直接在编译期就合并成常量"abcd"， 因此相同字面值常量"abcd"所对应的是同一个拘留字符串对象，自然地址也就相同。
  */
 /**
  * 注意：这个对初学者来说有个误区，有人说String str1=new String("abc"); str1=new String("cba");
  * 不是改变了字符串str1吗？那么你有必要先搞懂对象引用和对象本身的区别。这里我简单的说明一下，
  * 对象本身指的是存放在堆空间中的该对象的实例数据(非静态非常量字段)。
  * 而对象引用指的是堆中对象本身所存放的地址，一般方法区和Java栈中存储的都是对象引用，而非对象本身的数据。
  */
 /**
  * 结论：String常量的“+连接”  稍优于  String变量的“+连接”。 
      原因：测试①的"Heart"+"Raid"在编译阶段就已经连接起来，形成了一个字符串常量"HeartRaid"，并指向堆中的拘留字符串对象。
      运行时只需要将"HeartRaid"指向的拘留字符串对象地址取出1W次，存放在局部变量str中。这确实不需要什么时间。 
  */
 /**
  * ★ 镜头总结：
    (1) 在编译阶段就能够确定的字符串常量，完全没有必要创建String或StringBuffer对象。直接使用字符串常量的"+"连接操作效率最高。
    (2) StringBuffer对象的append效率要高于String对象的"+"连接操作。
    (3) 不停的创建对象是程序低效的一个重要原因。那么相同的字符串值能否在堆中只创建一个String对象那。
    显然拘留字符串能够做到这一点，除了程序中的字符串常量会被JVM自动创建拘留字符串之外，调用String的intern()方法也能做到这一点。当调用intern()时，
    如果常量池中已经有了当前String的值，那么返回这个常量指向拘留对象的地址。如果没有，则将String值加入常量池中，并创建一个新的拘留字符串对象。
  */
}
