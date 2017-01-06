package com.cyb.spring;

public class Chinese implements Person {
	private Axe axe;	
	private String name;
	private int age ;
	public Axe getAxe() {
		return axe;
	}

	public void setAxe(Axe axe) {
		this.axe = axe;
	}

	public void save() {
        System.out.println(this.name+"执行保存！");
	}	
	public void useAxe(){
		axe.chop();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}

