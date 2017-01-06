package com.cyb.log;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LogUtil {
	org.apache.log4j.Logger x;
		static Log logger =LogFactory.getLog(LogUtil.class);
		static Logger log = LoggerFactory.getLogger(LogUtil.class);
		public static void testTomcatLog(){
			logger.debug("This  is apache debug message");
	        logger.info("This is apache info message");
	        logger.warn("This is apache warn message");
	        logger.error("This is apache error message");
		}
		public static void testSLFLog(){
			log.debug("This is slf4j debug message");
	        log.info("This is slf4j info message");
	        log.warn("This is slf4j warn message");
	        log.error("This is slf4j error message");
		}
	    public static void main(String[] args) {
	    	testTomcatLog();
	    	log.info("********************************************************");
	    	testSLFLog();
	    }
	    public static  Log getLog_(Class<?> cls){
			return LogFactory.getLog(cls);
	    }
	    public static Logger getLog(Class<?> cls){
			return LoggerFactory.getLogger(cls);
	    }
}
