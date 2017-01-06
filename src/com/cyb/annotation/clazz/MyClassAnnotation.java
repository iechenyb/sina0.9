package com.cyb.annotation.clazz;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention (RetentionPolicy.RUNTIME)   
@Target (ElementType.TYPE)   
public   @interface  MyClassAnnotation   
{  
    String uri();  
    String desc();  
}  