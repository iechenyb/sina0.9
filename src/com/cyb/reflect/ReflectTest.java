package com.cyb.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.cyb.reflect.invoke.CallMethod;

public class ReflectTest {
	 @SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
	  Class clazz = Class.forName("com.cyb.reflect.UserBean");//这里的类名是全名。。有包的话要加上包名
	  Object obj = clazz.newInstance();
	  Field[] fields = clazz.getDeclaredFields();
	  //写数据
	  for(Field f : fields) {
		   PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
		   Method wM = pd.getWriteMethod();//获得写方法
		   String[] paramNames = MethodUtils.getMethodParamNames(UserBean.class,wM.getName());
		   Class[] paramtypes = MethodUtils.getParamTypes(UserBean.class,wM.getName());
			for(int i=0;i<paramNames.length;i++){
				System.out.println(wM.getName()+",paraname="+paramNames[i]+",type="+paramtypes[i]);
			}
		   //wM.invoke(obj, 2);//因为知道是int类型的属性，所以传个int过去就是了。。实际情况中需要判断下他的参数类型
	  }
	  //读数据
	  for(Field f : fields) {
		   PropertyDescriptor pd = new PropertyDescriptor(f.getName(), clazz);
		   Method rM = pd.getReadMethod();//获得读方法
		   //Integer num = (Integer) rM.invoke(obj);//因为知道是int类型的属性,所以转换成integer就是了。。也可以不转换直接打印
		   System.out.println(rM.getName()+"="+rM.invoke(obj));
	  }
	 }
	}