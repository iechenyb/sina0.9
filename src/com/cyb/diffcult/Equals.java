package com.cyb.diffcult;

import java.util.Date;

import com.cyb.date.DateUtil;

public class Equals {
  public static void main(String[] args) {
	Date d1 = new Date();
	Date d2 = new Date();
	Date d3 = DateUtil.calendar("20160923123050").getTime();
	Date d4 = DateUtil.calendar("20160923123051").getTime();
	Date d5 = DateUtil.calendar("20160923123051").getTime();
	System.out.println(d1.equals(d2));
	System.out.println(d4.equals(d3));
	System.out.println(d5.equals(d4));
}
}
