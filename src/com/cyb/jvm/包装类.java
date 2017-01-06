package com.cyb.jvm;

public class 包装类 {
  public static void main(String[] args) {
	  short s1=1;
	  /*s1=s1+1;*/// err 1是int s1是short result should is int
	  int s2 = s1+1;
  }
}
