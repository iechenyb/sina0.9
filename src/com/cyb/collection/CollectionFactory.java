package com.cyb.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionFactory {
   public static List<Integer> list = new ArrayList<Integer>();
   public static List<User> users = new ArrayList<User>();
   public static Set<Integer> set = new HashSet<Integer>();
   public static Map<String,String> map = new HashMap<String,String>();
   public static User user = new User();
   
   public static void build(int nums){
	   for(int i=0;i<nums;i++){
		   User user = new User();
		   user.setName("iechenyb"+i);
		   user.setPwd("don't tell you!"+i);
		   list.add(i);
		   set.add(i);
		   map.put("k1", "val"+i);
		   users.add(user);
	   }
	   user.setName("iechenyb");
	   user.setPwd("don't tell you!");
   }
   public static List<Integer> getList(){
	   return list;
   }
   public static Set<Integer> getSet(){
	   return set;
   }
   public static Map<String,String> getMap(){
	   return map;
   }
   public static User getUser(){
	   return user;
   }
}
