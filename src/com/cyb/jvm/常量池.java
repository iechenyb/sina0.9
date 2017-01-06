package com.cyb.jvm;
//当int取值-1~5采用iconst指令，取值-128~127采用bipush指令，取值-32768~32767采用sipush指令，取值-2147483648~2147483647采用 ldc 指令。
public class 常量池 {
	public String s1="a";
	
	public static void main(String[] args) {
		objPoolTest();
	}
	/**
	 * //浮点类型的包装类没有实现常量池技术
	 */
	// Byte,Short,Integer,Long,Character,Boolean
	@SuppressWarnings("unused")
	public static void objPoolTest() {
		int max = 123456789;
		int i40 = 40;
		int i41 = 40;
		int i540 = 540;
		int i541 = 540;
		Integer i1 = 40;
		Integer i2 = 40; // java在编译的时候,被翻译成-> Integer i2 = Integer.valueOf(40);
		Integer i3 = 0;
		Integer i4 = new Integer(40);
		Integer i5 = new Integer(40);
		Integer i6 = new Integer(0);		
		Integer i7 = -129;
		Integer i8 = -129; // 常量池范围 -128<x<=127		
		Integer i9 = Integer.valueOf(200);
		Integer i10 = Integer.valueOf(200);		
		Integer i11 = new Integer(200);
		Integer i12 = new Integer(160);
		Integer i13 = new Integer(40);		
		// 对于i1==i2+i3、i4==i5+i6结果为True，是因为，Java的数学计算是在内存栈里操作的，Java会对i5、i6进行拆箱操作，其实比较的是基本类型（40=40+0），他们的值相同，因此结果为True。
		Integer i14 = new Integer(1000);
		Integer i15 = new Integer(500);
		Integer i16 = new Integer(500);
		System.out.println("i=i0\t" + (i40 == i41));
		System.out.println("i1=i2\t" + (i1 == i2));
		System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));//true 自动拆装箱
		System.out.println("i4=i5\t" + (i4 == i5));//false
		System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));//true
		System.out.println("i7==i8 " + (i7 == i8));// false
		System.out.println("i9==i10 " + (i9 == i10));// false***
		System.out.println("i11==i12+i13  " + (i11 == i12 + i13));// true***
		System.out.println("i14==i15+i16  " + (i14 == i15 + i16));// true***
		/**
		 * 1.i和i0均是普通类型(int)的变量，所以数据直接存储在栈中，而栈有一个很重要的特性：栈中的数据可以共享。当我们定义了int i =
		 * 40;，再定义int i0 = 40; 这时候会自动检查栈中是否有40这个数据，如果有，i0会直接指向i的40，不会再添加一个新的40。
		 * 2.i1和i2均是引用类型，在栈中存储指针，因为Integer是包装类。由于Integer
		 * 包装类实现了常量池技术，因此i1、i2的40均是从常量池中获取的，均指向同一个地址， 因此i1=12。
		 * 3.很明显这是一个加法运算，Java的数学运算都是在栈中进行的
		 * ，Java会自动对i1、i2进行拆箱操作转化成整型，因此i1在数值上等于i2+i3。 4.i4和i5
		 * 均是引用类型，在栈中存储指针，因为Integer是包装类
		 * 。但是由于他们各自都是new出来的，因此不再从常量池寻找数据，而是从堆中各自new一个对象，
		 * 然后各自保存指向对象的指针，所以i4和i5不相等，因为他们所存指针不同，所指向对象不同。 5.这也是一个加法运算，和3同理。
		 * 6.d1和d2均是引用类型
		 * ，在栈中存储指针，因为Double是包装类。但Double包装类没有实现常量池技术，因此Doubled1=1.0; 相当于Double
		 * d1=new Double(1.0);，是从堆new一个对象，d2同理。因此d1和d2存放的指针不同，指向的对象不同，所以不相等。
		 */
		Short S1 = 127;
		Short S2 = 127;
		Short S3 = 200;
		Short S4 = 200;
		Short S5 = -128;
		Short S6 = -128;
		short s1 = 127;
		short s2 = 127;
		short s3 = 200;
		short s4 = 200;
		short s5 = -128;
		short s6 = -128;
		System.out.println(S1 == S2);// true
		System.out.println(S3 == S4);// false
		System.out.println(S5 == S6);// true [-128,127]
		Float F1 = 1.0f;
		Float F2 = 1.0f;
		Float F3 = 200.0f;
		Float F4 = 200.0f;
		float f1 = 100f;
		float f2 = 100f;
		float f3 = 500f;
		float f4 = 500f;
		System.out.println(f1==f2);
		System.out.println(F1==F2);
		System.out.println(F3==F4);
		long l1=100;
		long l2 =100;
		long l3=800;
		long l4=800;
		Long L1=100L;
		Long L2=100l;
		Long L3=800l;
		Long L4=800l;
		System.out.println(l1==l2);
		System.out.println(L1==L2);
		System.out.println(L3==L4);
		double d1 = 100d;
		double d2 = 100d;
		Double D1 = 1.0;
		Double D2 = 1.0;
		System.out.println("D1=D2\t" + (D1 == D2));
		System.out.println("d1=d2\t" + (d1 == d2));
		boolean b1 = true;
		boolean b2 = true;
		Boolean B1 = true;
		Boolean B2 = true;
		Boolean B3 = false;
		Boolean B4 = true;
		System.out.println(b1==b2);
		System.out.println(B1==B2);
		System.out.println(B2==B4);
		// 2.String类型也实现了常量池技术，但是稍微有点不同。String型是先检测常量池中有没有对应字符串，如果有，则取出来；如果没有，则把当前的添加进去。
		// 代码1
		String sa = "ab";
		String sb = "cd";
		String sab = sa + sb;
		String s = "abcd";
		System.out.println(sab == s); // false
		// 代码2
		String sc = "ab" + "cd";
		String sd = "abcd";
		System.out.println(sc == sd); // true
		// 代码1
		String sa1 = new String("Hello world");
		String sb1 = new String("Hello world");
		System.out.println(sa1 == sb1); // false
		// 代码2
		String sc1 = "Hello world";
		String sd1 = "Hello world";
		System.out.println(sc1 == sd1); // true
		/**
		 * 代码1中局部变量sa1,sb1中存储的是JVM在堆中new出来的两个String对象的内存地址。
		 * 虽然这两个String对象的值(char[]存放的字符序列)都是"Hello world"。 因此"=="比较的是两个不同的堆地址。
		 * 代码2中局部变量sc1,sd1中存储的也是地址，但却都是常量池中"Hello world"指向的堆的唯一的那个拘留字符串对象的地址
		 * 。自然相等了。
		 */
		String ssab = "chenyb".intern();
		String ssac = "chenyb".intern();
		String ssad = new String("chenyb").intern();
		String ssae = new String("chenyb");
		System.out.println(ssab == ssac);
		System.out.println(ssac == ssad);
		System.out.println(ssac == ssae);// false
	}
}
