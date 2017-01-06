package com.cyb.jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
//ldap服务器搭建
public class LdapDataBind {
	 @SuppressWarnings("unchecked")
	public static void main(String[]args){

	       //创建Hashtable以存储JNDI将用于连接目录服务的环境变量

	        Hashtable hs = new Hashtable();

	        //设置连接LDAP的实现工厂

	        hs.put(Context.INITIAL_CONTEXT_FACTORY,

	                      "com.sun.jndi.ldap.LdapCtxFactory");

	        // 指定LDAP服务器的主机名和端口号

	        hs.put(Context.PROVIDER_URL, "ldap://localhost:3899");

	        //给环境提供认证方法,有SIMPLE、SSL/TLS和SASL

	        hs.put(Context.SECURITY_AUTHENTICATION, "simple");

	        //指定进入的目录识别名DN

	        hs.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");

	        //进入的目录密码

	        hs.put(Context.SECURITY_CREDENTIALS, "password");

	        try {

	           // 得到初始目录环境的一个引用

	           DirContext ctx = new InitialDirContext(hs);

	           // 新建一个对象

	           Persons perObj = new Persons("jordan","40");

	           //绑定对象

	           ctx.rebind ("uid = Jordan,ou = Bull,o = NBA ",perObj);

	           System.out.println("bind object object success " );

	             /*实例化一个属性集合*/

	             Attributes  attrs =  new BasicAttributes(true);

	             /*建立一个属性,其属性名为"mail"*/

	            Attribute  personMail  = new BasicAttribute("mail");

	            //设置属性"mail"的值为"xie@163.com"、"liu@sina.com.cn"、  "xyh@powerise.com.cn"

	            personMail.add("xie@163.com");

	            personMail.add("liu@sina.com.cn");

	            personMail.add("xyh@powerise.com.cn");

	             attrs.put(personMail);

	             /*建立一个属性,其属性名为"uid",值为001*/

	            attrs.put("uid","001");

	            /*建立一个属性,其属性名为"cn",值为jordan1*/

	            attrs.put("cn","jordan1");

	            /*建立一个属性,其属性名为"sn",值为NBA */

	            attrs.put("sn","NBA");

	            /*建立一个属性,其属性名为"ou",值为bull */

	            attrs.put("ou","bull");

	            System.out.println("bind object object success " );

	            /* 在识别名为DN的目录中增加一个条目*/

	            ctx.createSubcontext("uid = Jordan, ou = Wizzard,o=NBA",attrs);

	           //关闭初始目录环境

	           ctx.close();

	        } catch (NamingException ex) {

	           System.err.println("bind object fail: " + ex.toString());

	        } 

	   }
}
