package com.cyb.comconection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.cyb.mybatis.User;

public class ConnectionUtils<T> {
	public  Connection conn = null;  
	public QueryRunner query = null;
	public ConnectionUtils(){
	    String url = "jdbc:h2:tcp://localhost/~/trade;AUTO_SERVER=true";  
	    String jdbcDriver = "org.h2.Driver";  
	    String user = "sa";  
	    String password = "111111";  
	    DbUtils.loadDriver(jdbcDriver); 
	    query = new QueryRunner();
	    try {  
	        conn = DriverManager.getConnection(url, user, password);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	        //DbUtils.closeQuietly(conn);  
	    }  
	}
	public  void close(){
		DbUtils.closeQuietly(conn); 
	}
	public ConnectionUtils(Connection conn){
		if(this.conn == null){
			this.conn = conn;
		}else{
			new ConnectionUtils<T>();
		}
		if(query==null){
			query = new QueryRunner();
		}
	}
	public static void main(String[] args) throws SQLException {
	     String sql = "select username,password from usr";
	     ConnectionUtils<User> dbUtils = new ConnectionUtils<User>();
	     List<User> users = dbUtils.queryForList(sql,User.class);
	     for (int i = 0; i < users.size(); i++) {  
	        	User p = users.get(i);  
	            System.out.println("name:" + p.getUsername() + ",pwd:" + p.getPassword());  
	      }  
	     ConnectionUtils<Map<String,Object>> dbUtils1 = new ConnectionUtils<Map<String,Object>>();
	     List<Map<String,Object>> maps = dbUtils1.queryForMap(sql,Map.class);
	     System.out.println(maps);
	     
	     User user = dbUtils.queryForObject(sql,User.class);
	     System.out.println(user.getUsername()+","+user.getPassword());
	}
	 /*Topic newlyTopic=null;
	 2         QueryRunner runner= new QueryRunner(JdbcUtil.getDataSource());
	 3         String sql ="select * from topic where type_id= ? order by time desc";
	 4         Object[] params={typeId};
	 5         newlyTopic= runner.query(sql,new BeanHandler<Topic>(Topic.class),params);
	 6         return newlyTopic;*/
	public  List<T> queryForList(String sql,Class<T> cls) throws SQLException{
		List<T> results = this.query.query(conn, sql, new BeanListHandler<T>(cls));    
		return results;
	}
	public  T queryForObject(String sql,Class<T> cls) throws SQLException{
		T t = this.query.query(conn, sql, new BeanHandler<T>(cls));  
		return t;
	}
	public  List<T> queryForMap(String sql,@SuppressWarnings("rawtypes") Class cls) throws SQLException{
		@SuppressWarnings("unchecked")
		List<T> results = (List<T>) this.query.query(conn, sql, new MapListHandler());         
		return results;
	}
	
	public  void insert(){
        String sql ="insert into topic(name,author,content,time,type_id) values(?,?,?,?,?)";
        Object[] params={};
        try {
            //事务开始
            this.query.update(sql,params);
            //事务提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void update(String sql){
		try {
            //事务开始
            this.query.update(conn,sql);
            //事务提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void update(){
        String sql ="update topic set name=? , content=? , time=? where id= ?";
        Object[] params={};
        try {
            //事务开始
            this.query.update(sql,params);
            //事务提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}  
