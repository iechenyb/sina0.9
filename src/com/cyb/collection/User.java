package com.cyb.collection;

import com.cyb.shejimoshi.原型模式;

public class User extends 原型模式 implements Comparable<User>   {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String staticValue="123456789";
	private Integer id;
	private String name;
	private String pwd;
    public User(Integer id,String name,String pwd){
    	this.id = id;
    	this.name = name;
    	this.pwd = pwd;
    }
    public User(String name,String pwd){
    	this.name = name;
    	this.pwd = pwd;
    }
    public User(){
    	
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
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}
	public String toString(){
		return this.id+"#"+this.name+"#"+this.pwd;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public int compareTo(User o) {
		if (id<o.id) //这里比较的是什么 sort方法实现的就是按照此比较的东西从小到大排列
			return - 1 ;  
		if (id>o.id)  
		    return 1 ;  
		return 0;
	}
}
