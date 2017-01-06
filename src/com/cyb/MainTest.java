package com.cyb;

public class MainTest {
	public static void main(String[] args) {
		String date = "20160520";
		date = date.substring(0, 4)+date.substring(4, 6)+date.substring(6, 8);
		System.out.println(date);
	}
}
