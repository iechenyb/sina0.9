package com.cyb.jmx;

public interface ControllerMBean {
	//属性  
    public void setName(String name);  
    public String getName();  
   //属性  
    public void setAge(String age);  
    public String getAge();  
      
    //操作  
    /** 
     * 获取当前信息 
     * @return 
     */  
    public String status();  
    public void start(String czy);  
    public void stop(String czy);  
}
