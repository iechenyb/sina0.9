package com.cyb.reflect.invoke;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.cyb.collection.User;
import com.cyb.reflect.MethodUtils;

//http://blog.csdn.net/qq_14996421/article/details/51598611
public class CallMethod {
	@SuppressWarnings({ "rawtypes", "unused" })
	public static Class[] getParamTypes(String methodName,Class clazz) {
		Class<?>[] paramsClass = null;
		Method[] methods = clazz.getDeclaredMethods(); // 取得这个类的所有方法
		if (methods != null) {
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (methodName.equals(method.getName())) { // 取得本方法，这个方法是test，所以就用test比较
					paramsClass = method.getParameterTypes(); // 取得参数列表的所有类
					if (paramsClass != null) {
						for (Class<?> class1 : paramsClass) {
							//System.out.println(class1.getName());
						}
					}
					break;
				}
			}
		}
		return paramsClass;
	}

	public static void main(String[] args) {
		CallMethod call = new CallMethod();
		try {
			Method method1 = CallMethod.class.getMethod("sunvins",(Class[]) null);
			Object obj1 = method1.invoke(call, (Object[]) null);
			System.out.println("object1="+obj1);// null
			getParamTypes("sunvins1",CallMethod.class);
			//Method method2 = CallMethod.class.getMethod("sunvins1",	String.class, int.class);
			Method method2 = CallMethod.class.getMethod("sunvins1",	getParamTypes("sunvins1",CallMethod.class));
			
			Map<String,Object> paramsHttp = new HashMap<String,Object>();
			paramsHttp.put("para1", "chenybslsl");
			paramsHttp.put("para2", 78965);
			paramsHttp.put("throwException", false);
			Object[] params = new Object[paramsHttp.size()];
			//根据map组装参数数组*
			String[] paramNames1 = MethodUtils.getMethodParamNames(CallMethod.class,"sunvins1");
			for(int i=0;i<paramNames1.length;i++){//根据http请求封装参数
				params[i]= paramsHttp.get(paramNames1[i]); 
			}
			//Object obj2 = method2.invoke(call, "smile", 7);//指定参数值
			Object obj2 = method2.invoke(call, params);//使用参数数组（直接指定数组）
			System.out.println("object2="+obj2);// null
			
			Method method3 = CallMethod.class.getMethod("sunvins2",(Class[]) null);
			Object returnValue = (String) method3.invoke(call, (Object[]) null);
			System.out.println(returnValue);
			Method method4 = CallMethod.class.getMethod("sunvins3",	String.class);
			Object returnValue1 = (User) method4.invoke(call, "chenyb");
			System.out.println("return calssname: " + returnValue1.getClass());
			System.out.println("return canonicalName: "
					+ returnValue1.getClass().getCanonicalName());
			if (returnValue1 instanceof com.cyb.collection.User) {
				System.out.println(((com.cyb.collection.User) returnValue1)
						.getName());
			}
			if (returnValue1.getClass().getCanonicalName()
					.equals("com.cyb.collection.User")) {
				System.out.println(((com.cyb.collection.User) returnValue1)
						.getName());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 无参无返回值
	 */
	public void sunvins() {
		System.out.println("---haha");
	}

	/**
	 * 带参数
	 * 
	 * @param s
	 * @param i
	 * @throws Exception 
	 */
	public void sunvins1(String para1, int para2,boolean throwException) throws Exception {
		if(throwException){
			throw new Exception("Invoke method exception!");
		}
		System.out.println("--para1=" + para1);
		System.out.println("--para2=" + para2);
	}

	/**
	 * 带返回值的
	 * 
	 * @return
	 */
	public String sunvins2() {
		return "well";
	}

	/**
	 * 带返回值的
	 * 
	 * @return
	 */
	public User sunvins3(String name) {
		User user = new User();
		user.setName(name);
		return user;
	}
}
