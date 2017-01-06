package com.cyb.shejimoshi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.cyb.collection.User;

public class 原型模式 implements Cloneable, Serializable {  
	  
    private static final long serialVersionUID = 1L;  
    private String string;  
  
    private 可序列化对象 obj;  
  
    /* 浅复制 */  
    public Object clone() throws CloneNotSupportedException {  
                    原型模式 proto = (原型模式) super.clone();  
        return proto;  
    }  
  
    /* 深复制 */  
    public Object deepClone() throws IOException, ClassNotFoundException {  
        /* 写入当前对象的二进制流 */  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        ObjectOutputStream oos = new ObjectOutputStream(bos);  
        oos.writeObject(this);  
        /* 读出二进制流产生的新对象 */  
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
        ObjectInputStream ois = new ObjectInputStream(bis);  
        return ois.readObject();  
    }  
  
    public String getString() {  
        return string;  
    }  
  
    public void setString(String string) {  
        this.string = string;  
    }  
  
    public 可序列化对象 getObj() {  
        return obj;  
    }  
  
    public void setObj(可序列化对象 obj) {  
        this.obj = obj;  
    }  
    public static void main(String[] args) {
		User user = new User("chenyb","11");
		System.out.println( user);
		User copyUser;
		User copyUser2;
		try {
			copyUser = (User) user.deepClone();
			System.out.println(copyUser);//引用地址都一样，深度复制后
			copyUser2 = (User) user.clone();
			System.out.println(copyUser2);//引用地址也都一样，克隆后
			/**
			 *  System.out.println()方法会自动调用参数的toString()方法
				如果你传进去的参数没有重写过toString()方法，那么就会输出改被打印对象的hash值
				如果有重写toString()方法，那么就会按照重写后的toString()方法来输出内容
			 */
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
	}
  
}  