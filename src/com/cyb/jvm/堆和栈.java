package com.cyb.jvm;

import java.util.ArrayList;
import java.util.HashMap;

import com.cyb.collection.User;

public class 堆和栈 {
  public static void main(String[] args) {
	User user1 =  new User("chenyb1","1111");
	User user2 =  new User("chenyb2","2222");
	User user4 =  new User("chenyb1","1111");
    User user3 = user1;
    ArrayList<Integer> list = new ArrayList<Integer>();
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    System.out.println(user1==user2);//false //new的不对对象，系统分配新的对空间
    System.out.println(user1==user3);//true //对象的引用相同，则相同
    System.out.println(user4==user1);//false //对象的所有属性值相同，则对象不一定相同
    int from[] = new int[]{1,2,3,4};
    int to[] = from.clone();
    System.out.println(from.length==to.length);//true
    System.out.println(from==to);//false
    for(int i=0;i<from.length;i++){
    	System.out.print(from[i]+","+to[i]+" ");
    	list.add(i);
    	map.put(i, i);
    }
    System.out.println();
    ArrayList<Integer> listTo = (ArrayList<Integer>) list.clone();
    HashMap<Integer,Integer> mapTo = (HashMap<Integer,Integer>) map.clone();
    System.out.println(list == listTo);//false
    System.out.println(map == mapTo);//false
    for(int i=0;i<listTo.size();i++){
    	System.out.print(list.get(i)+","+listTo.get(i)+" ");
    }
    System.out.println(map+">>"+mapTo);
    listTo.set(2, 90); map.put(1, 1000);
    for(int i=0;i<listTo.size();i++){
    	System.out.print(list.get(i)+","+listTo.get(i)+" ");
    }
    System.out.println(map+">>"+mapTo);
  }
}
