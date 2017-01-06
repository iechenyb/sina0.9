package com.cyb.annotation.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParsing {
	public static void main(String[] args) {
		try {
			for (Method method : AnnotationParsing.class
					.getClassLoader()
					.loadClass(("com.cyb.annotation.AnnotationExample"))
					.getMethods()) {
				// checks if MethodInfo annotation is present for the method
				if (method.isAnnotationPresent(MethodAnnotation.class)) {
					try {
						// iterates all the annotations available in the method
						for (Annotation anno : method.getDeclaredAnnotations()) {
							//System.out.println("Annotation in Method '"	+ method + "' : " + anno);
						}
						MethodAnnotation methodAnno = method
								.getAnnotation(MethodAnnotation.class);
						if (methodAnno.revision() == 1) {
							//System.out.println("Method with revision no 1 = "+ method);
						}
						System.out.println(methodAnno.author()+","+methodAnno.comments()+","+methodAnno.date()+","+methodAnno.revision());
					} catch (Throwable ex) {
						ex.printStackTrace();
					}
				}
			}
		} catch (SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
