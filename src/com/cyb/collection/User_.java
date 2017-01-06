package com.cyb.collection;

public class User_ {
	private String name;
	private String pwd;
    public User_(String name,String pwd){
    	this.name = name;
    	this.pwd = pwd;
    }
    public User_(){
    	
    }
    public User_(String name){
    	this.name = name;
    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String toString(){
		return this.name+"#"+this.pwd;
	}
}
