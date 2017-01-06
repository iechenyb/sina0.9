package com.cyb.annotation.method;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AnnotationExample {
	public static void main(String[] args) {
		
	}

	@Override
	@MethodAnnotation(author = "chenyb", comments = "Main1 method", date = "Nov 17 2012", revision = 1)
	public String toString() {
		return "Overriden toString method";
	}

	@Deprecated
	@MethodAnnotation(comments = "deprecated method", date = "Nov 17 2012")
	public static void oldMethod() {
		System.out.println("old method, don't use it.");
	}

	@MethodAnnotation(author = "Pankaj", comments = "Main2 method", date = "Nov 17 2012", revision = 10)
	public static void genericsTest() throws FileNotFoundException {
		List<String> l = new ArrayList<String>();
		l.add("abc");
		oldMethod();
	}
}
