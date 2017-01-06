package com.cyb.collection;

public class 对象相等 {
	 public static void main(String[] args) {
		User user0 = new User("chenyb","11111");
		User user1 = new User("chenyb","11111");
		System.out.println("重写hashcode和equals比较结果："+user0.equals(user1));
		
		User_ user3 = new User_("chenyb","11111");
		User_ user4 = new User_("chenyb","11111");
		System.out.println("未重写hashcode和equals方法结果："+user3.equals(user4));
				
	}
}
