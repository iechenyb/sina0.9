package com.cyb.ejb;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;
//http://qujianfeng.iteye.com/blog/793409
public class ConverterClient {
	public ConverterClient(){
	}
	public String toString(){
		return super.toString();
	}
	static int x;
	static {x+=5;}
	static int a[] = new int[10];
	public static void main(String[] args) {
	       try {
	    	   int   i , j ;
				int  a[ ] = { 2,1,3,5,4};
		   		for  ( i = 0 ; i < a.length-1; i ++ ) {
			   		int  k = i;
			   		for  ( j = i ; j < a.length ;  j++ )
				   		if  ( a[j]<a[k] )  k = j;
				  		int  temp =a[i];
				   		a[i] = a[k];
				   		a[k] = temp;
		   		}
		   		for  ( i =0 ; i<a.length; i++ )
			  		 System.out.print(a[i]+"  ");
	       	         System.out.println( );
	       	         System.out.println(10%-3);
	       	      System.out.println(10/-3);
	    	   
	          /* // 创 建一个JNDI naming contest
	           Context initial = new InitialContext();
	           //Parent x;
	           // 从JNDI 中以MyConverter 名子来定位到对象(在发布名称指定了JNDI名称)
	            //Object objref = initial.lookup("java:comp/env/ejb/ConverterEJB");
	           Object objref = initial.lookup("MyConverter");
	          
	           // 通 过objref 得到ConverterHome 本地接口
	           ConverterHome home = (ConverterHome)PortableRemoteObject.narrow(
	                   objref, ConverterHome.class);
	          
	           // 再 由Home 接口的create 方法来创建一个服务器上的EJB实例
	           Converter currencyConverter = home.create();
	          
	           // 调 用EJB 中的方法
	           double amount = currencyConverter.dollarToYen(100.00);
	           System.out.println(String.valueOf(amount));
	           amount = currencyConverter.yenToEuro(100.00);
	           System.out.println(String.valueOf(amount));*/
	       }catch(Exception e){
	    	   e.printStackTrace();
	       }
	}
	static{
		x=x/5;
	}
}
