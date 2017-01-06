package com.cyb.annotation.method;

public class HelloWorldStub {  
	  
    @HelloWorldAnnotation(name = "小明")  
    public String sayHello(String name) {  
        if (name == null ) {  
            name = "";  
        }         
        return name + " say hello world!";  
    }  
}  