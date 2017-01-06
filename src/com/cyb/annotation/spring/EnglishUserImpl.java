package com.cyb.annotation.spring;

public class EnglishUserImpl implements IUser {  
    @Override  
    public void login() {  
        System.err.println("User Login！");  
    }  
} 