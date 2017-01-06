package com.cyb.dbpool.case1;

import java.util.ArrayList;  
import java.util.List;  

import com.cyb.h2.H2Manager;
/** 
 * 初始化，模拟加载所有的配置文件 
 * @author Ran 
 * 
 */  
public class DBInitInfo {  
    public  static List<DBbean>  beans = null;  
    static{  
        beans = new ArrayList<DBbean>();  
        // 这里数据 可以从xml 等配置文件进行获取  
        // 为了测试，这里我直接写死  
        /*DBbean beanOracle = new DBbean();  
        beanOracle.setDriverName("oracle.jdbc.driver.OracleDriver");  
        beanOracle.setUrl("jdbc:oracle:thin:@7MEXGLUY95W1Y56:1521:orcl");  
        beanOracle.setUserName("mmsoa");  
        beanOracle.setPassword("password1234");  
        beanOracle.setMinConnections(5);  
        beanOracle.setMaxConnections(100);  
        beans.add(beanOracle);  
        beanOracle.setPoolName("oraclePool");  */
        
        H2Manager.start();
        DBbean beanH2 = new DBbean();  
        beanH2.setDriverName("org.h2.Driver");  
        beanH2.setUrl("jdbc:h2:tcp://localhost/~/chenyb");  
        beanH2.setUserName("");  
        beanH2.setPassword("");  
        beanH2.setMinConnections(1);  
        beanH2.setMaxConnections(3);  
        beanH2.setPoolName("h2Pool");  
        beans.add(beanH2);  
    }  
} 
