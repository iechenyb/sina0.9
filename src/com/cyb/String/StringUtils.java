package com.cyb.String;

public class StringUtils {
 public static void main(String[] args) {
	String str="1234567890";
	StringBuilder sb = new StringBuilder(str);
	System.out.println(sb.replace(2, 8, "******"));
 }
 
}
