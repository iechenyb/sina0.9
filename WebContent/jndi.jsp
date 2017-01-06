<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,javax.sql.*,javax.naming.*" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>JNDI数据源测试</title>
  </head>
  
  <body>
        <%
            Connection connOracle = null;
            try {
                //1、初始化名称查找上下文
                Context ctx = new InitialContext();
                //InitialContext ctx = new InitialContext();亦可 
                //2、通过JNDI名称找到DataSource,对名称进行定位java:comp/env是必须加的,后面跟的是DataSource名
                /*
                DataSource名在web.xml文件中的<res-ref-name>oracleDataSource</res-ref-name>进行了配置
                 <!--Oracle数据库JNDI数据源引用 -->
                 <resource-ref>
                      <description>Oracle DB Connection</description>
                      <res-ref-name>oracleDataSource</res-ref-name>
                      <res-type>javax.sql.DataSource</res-type>
                      <res-auth>Container</res-auth>
                 </resource-ref>
                */
                DataSource ds = (DataSource)ctx.lookup("java:comp/env/oracleDataSource");
                //3、通过DataSource取得一个连接
                connOracle = ds.getConnection();
                out.println("Oracle Connection pool connected !!");
                               
                DataSource ds11 = (DataSource)ctx.lookup("java:comp/env/oracle");
                //3、通过DataSource取得一个连接
                Connection connOracle11 = ds11.getConnection();
                out.println("Oracle Connection pool connected !!");
                
                DataSource ds22 = (DataSource)ctx.lookup("java:comp/env/mysql");
                //3、通过DataSource取得一个连接
                Connection connOracle22 = ds22.getConnection();
                out.println("Oracle Connection pool connected !!");
                //4、操作数据库
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //5、关闭数据库，关闭的时候是将连接放回到连接池之中
                connOracle.close();
            }
        %>
        <hr/>
        <%
            Connection connMySQL = null;
            try {
                //1、初始化名称查找上下文
                Context ctx = new InitialContext();
                //InitialContext ctx = new InitialContext();亦可 
                //2、通过JNDI名称找到DataSource,对名称进行定位java:comp/env是必须加的,后面跟的是DataSource名
                /*
                DataSource名在web.xml文件中的<res-ref-name>mysqlDataSource</res-ref-name>进行了配置
                  <!--MySQL数据库JNDI数据源引用 -->
                  <resource-ref>
                      <description>MySQL DB Connection</description>
                      <res-ref-name>mysqlDataSource</res-ref-name>
                      <res-type>javax.sql.DataSource</res-type>
                      <res-auth>Container</res-auth>
                  </resource-ref>
                */
                DataSource ds = (DataSource)ctx.lookup("java:comp/env/mysqlDataSource");
                //3、通过DataSource取得一个连接
                connMySQL = ds.getConnection();
                out.println("MySQL Connection pool connected !!");
                //4、操作数据库
            } catch (NamingException e) {
                System.out.println(e.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //5、关闭数据库，关闭的时候是将连接放回到连接池之中
                connMySQL.close();
            }
        %>
        <hr/>
    </body>
</html>