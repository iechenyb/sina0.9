package com.cyb.annotation.spring;

public class ChineseUserImpl implements IUser {  
    @Override  
    public void login() {  
        System.err.println("用户登录！");  
    }  
}
