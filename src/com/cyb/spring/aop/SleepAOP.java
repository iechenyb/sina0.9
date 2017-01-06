package com.cyb.spring.aop;


   public class SleepAOP {  
  
    public SleepAOP(){  
          
    }  
      
    public void sleeppoint(){}  
      
    public void beforeSleep(){  
        System.out.println("睡觉前要脱衣服!");  
    }  
      
    public void afterSleep(){  
       System.out.println("睡醒了要穿衣服！");  
    }  
      
}  

/*public class SleepAOP implements MethodBeforeAdvice, AfterReturningAdvice {  
		Log log = LogFactory.getLog(SleepAOP.class);  
		*//**
		 * 继承方法使用的是非<aop:config>
		 *//*
	    public void afterReturning(Object arg0, Method arg1, Object[] arg2,  
	            Object arg3) throws Throwable {  
	        log.info("穿衣服！吃饭饭!");  
	    }  
	  
	    public void before(Method arg0, Object[] arg1, Object arg2)  
	            throws Throwable {  
	        log.info("脱衣服！睡觉觉！");  
	    }  	  
	    public void beforeSleep() throws Throwable {  
	         log.info("[脱衣服！睡觉觉！]");
	    }  
	  
	    public void afterSleep() throws Throwable {  
	        log.info("[穿衣服！吃饭饭!]");  
	    }   
	    public void afterThrow(){//Exception ex
	    	log.info("出现异常了呢！");
	    }
	    public void afterReturn(){
	    	log.info("返回值已返回！");
	    }
	    public void around(ProceedingJoinPoint pjp) throws Throwable{
	    	log.info("around before method!");
	    	Object retVal = pjp.proceed(); 
	    	log.info("around after method!");
	    	log.info("around retVal="+retVal);
	    }
}*/
