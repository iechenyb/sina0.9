package com.cyb.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class SetStudy {
  public static void main(String[] args) {
	  /*Set<User_> stus = new TreeSet<User_>();
	  stus.add(new User_("f"));
	  stus.add(new User_("b"));
	  stus.add(new User_("a"));
	  System.out.println(stus);//报错*/
	  Set<User> stus1 = new TreeSet<User>();
	  stus1.add(new User(5,"",""));
	  stus1.add(new User(3,"",""));
	  stus1.add(new User(1,"",""));
	  System.out.println(stus1.iterator());//
	  Iterator<User> it=  stus1.iterator();
	  while(it.hasNext()){
		  System.out.println(it.next());
	  }
	  TreeSet<String> ts = new TreeSet<String>();
	  ts.add("1");
	  ts.add("2");
	  ts.add("3");
	  ts.add("4");
	  System.out.println("treeset 有序："+ts);
	  HashSet<String> hs = new HashSet<String>();
	  hs.add("1");
	  hs.add("2");
	  hs.add("3");
	  hs.add("4");
	  hs.add("5");
	  hs.add("6");
	  hs.add("7");
	  hs.add("8");
	  System.out.println("hashset 无序："+hs);
	  
}
}
