package com.cyb.spring.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.spring.MyApplicationContext;

/**
 * 模拟spring的依赖注入
 * @author DHUser
 *
 */
public class AOPTest {
	Log log = LogFactory.getLog(AOPTest.class); 
	public MyApplicationContext ac = null;
	public void init(){
		//从resource加载
		ac = new MyApplicationContext("applicationContext_studyaop.xml");
	}
    public void testSpringAOP() {
    	 Sleepable sleeper = (Sleepable) ac.getBean("human");  
         sleeper.sleep();  
         log.info("-------------------");
         sleeper.sleep("chenyb1");  
         log.info("-------------------");
         sleeper.sleep1("chenyb1");  
    }
    public static void main(String[] args) {
    	AOPTest t = new AOPTest();
    	t.init();t.testSpringAOP();
	}
}
