package com.cyb.annotation.value;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)  
public @interface Hidden {  
    boolean value();  
}  