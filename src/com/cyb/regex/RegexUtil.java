package com.cyb.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	public void findEnglishSentence() {
		String str = "ag1605SUN公司被Oracle收购，是否意味着java被逼上了死路？";
		String s = "\\d+.\\d+|\\w+";
		Pattern pattern = Pattern.compile(s);
		Matcher ma = pattern.matcher(str);

		while (ma.find()) {
			System.out.println(ma.group());
		}
	}

	public static void findStartEn() {
		String str = "ag1605";
		String s = "[a-zA-Z]+";
		Pattern pattern = Pattern.compile(s);
		Matcher ma = pattern.matcher(str);
		if (ma.find())
			System.out.println(ma.group());
		/*
		 * while(ma.find()){ System.out.println(ma.group()); }
		 */
	}

	public static void main(String[] args) {

		findStartEn();
	}
}
