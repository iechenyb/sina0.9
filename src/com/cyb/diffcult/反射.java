package com.cyb.diffcult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class 反射 {
 public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	String s= "chenyb";
	Method m = s.getClass().getMethod("toUpperCase");
	System.out.println(m.invoke(s));
}
}
