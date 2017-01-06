package com.cyb.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisMain {
	public static void main(String[] args) throws IOException {
        //mybatis的配置文件
        String resource = "com/cyb/mybatis/mybatis.xml";
        SqlSession session  = getSession0(resource);
        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String sqlKey = "getUser";//映射sql的标识字符串com.cyb.mybatis.mapper.getUser
        //执行查询返回一个唯一user对象的sql
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("xx", 1);
        param.put("yy", 1);
        param.put("zz", 2);
        Map user = session.selectOne(sqlKey, param);
        System.out.println(user);
        param.put("xx", 11);
      /*  int x = session.delete("delUser", param);
        System.out.println("del num = "+x);*/
    }
	
	public static SqlSession getSession0(String resource){
		//使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        InputStream is = MyBatisMain.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        SqlSession session = sessionFactory.openSession();
        return session;
	}
	public static SqlSession getSession1(String resource){
		Reader reader;
		SqlSession session = null ;
		try {
			reader = Resources.getResourceAsReader(resource);
	        //构建sqlSession的工厂
	        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
	        //创建能执行映射文件中sql的sqlSession
	        session = sessionFactory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		} 
        return session;
	}
}
