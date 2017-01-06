package com.cyb.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 集合排序 {
	 public static void main(String[] args) {
	  List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	  Map<String, String> data1 = new HashMap<String, String>();
	  Map<String, String> data2 = new HashMap<String, String>();
	  Map<String, String> data3 = new HashMap<String, String>();
	  data1.put("BEGIN_DATA", "2014-01-05");
	  data2.put("BEGIN_DATA", "2014-01-01");
	  data3.put("BEGIN_DATA", "2014-01-08");
	  list.add(data1);
	  list.add(data2);
	  list.add(data3);
	  // 排序前
	  System.out.println("Before sort.");
	  for (Map<String, String> m : list) {
	   System.out.println(m);
	  }
	  // 排序
	  Collections.sort(list, new MapComparator());
	  // 排序后
	  System.out.println("After sort.");
	  for (Map<String, String> m : list) {
	   System.out.println(m);
	  }
	 }

	 public static class MapComparator implements Comparator<Map<String, String>> {
	  @Override
	  public int compare(Map<String, String> o1, Map<String, String> o2) {
	   String b1 = o1.get("BEGIN_DATA");
	   String b2 = o2.get("BEGIN_DATA");
	   if (b2 != null) {
	    return b1.compareTo(b2);
	   }
	   return 0;
	  }
	 }
	}
