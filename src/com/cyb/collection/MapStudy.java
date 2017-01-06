package com.cyb.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class MapStudy {
 public static void main(String[] args) {
	/**
	 * HashMap和Hashtable的区别。   都属于Map接口的类，实现了将惟一键映射到特定的值上。   
	 * HashMap 类没有分类或者排序。它允许一个 null 键和多个 null 值。   
	 * Hashtable 类似于 HashMap，但是不允许 null 键和 null 值。它也比 HashMap 慢，因为它是同步的。
	 * LinkedHashMap 有序
	 */
	 HashMap<String,String> hm = new HashMap<String,String>();
	 hm.put(null, null);
	 hm.put(null, null);
	 hm.put("null", null);
	 System.out.println(hm);
	 Hashtable table = new Hashtable();
	// table.put("null", null);
	// System.out.println(table);//Exception in thread "main" java.lang.NullPointerException
	 //table.put(null, null);//Exception in thread "main" java.lang.NullPointerException
	 LinkedHashMap<String,String> lhm = new LinkedHashMap<String,String>();
	 lhm.put("1", "1");
	 lhm.put("2", "1");
	 lhm.put("3", "1");
	 System.out.println(lhm);
	 Map<User,User> treeMap = new TreeMap<User,User>();
	 treeMap.put(new User(5,"",""), new User(5,"",""));
	 treeMap.put(new User(3,"",""), new User(3,"",""));
	 treeMap.put(new User(1,"",""), new User(1,"",""));
	 System.out.println(treeMap.keySet());
	 Map<User_,User> treeMap1 = new TreeMap<User_,User>();
	 treeMap1.put(new User_("f"), new User(5,"",""));
	 treeMap1.put(new User_("a"), new User(3,"",""));
	 treeMap1.put(new User_("e"), new User(1,"",""));
	 System.out.println(treeMap1.keySet());
  }
}
