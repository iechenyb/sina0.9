package com.cyb.fanxing;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class HibernateBaseDao<T> implements BaseDao<T> {  
    private Class<T> entityClass;  
  
    /** 
     * 这个通常也是<a href="http://lib.csdn.net/base/javaee" class='replace_word' title="Java EE知识库" target='_blank' style='color:#df3434; font-weight:bold;'>hibernate</a>的取得子类class的方法 
     *  
     * @author "yangk" 
     * @date 2010-4-11 下午01:51:28 
     */  
	@SuppressWarnings("unchecked")
	public HibernateBaseDao() {  
        Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
        entityClass = (Class<T>) params[0];  
    }  
  
    @Override  
    public T get(String id) {  
        try {  
            return entityClass.newInstance();  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
}  
