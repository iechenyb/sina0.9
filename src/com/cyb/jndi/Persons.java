package com.cyb.jndi;

import java.io.Serializable;

public class Persons  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String Name = "";

    String Age =""  ;

    public Persons () {

    }

    //构造函数,用于给变量赋值

    public Persons (String namePara,String age) {

        Name = namePara;

        Age = age;

    }

    //用于返回变量Name的值

    public String getName() {

        return Name;

    }

    //用于返回变量Age的值

    public String getAge () {

    return  Age;

    }
}