package com.cyb.jvm.classloader;
import com.cyb.jvm.Chenyb;
public class IEChenyb implements Chenyb {
 public String name="iechenyb";
 public IEChenyb(String initValue){
   System.out.println("执行构造器方法："+initValue);
 }
 public IEChenyb(){
   System.out.println("default执行构造器方法");
 }
 public void print(String xxx){
	 if(xxx!=null){
	   this.name = xxx;
	 }
  	 System.out.println(" hello,my name is "+name);
 }
}
