package com.cyb.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.cyb.reflect.invoke.CallMethod;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class MethodUtils {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		
		/*String[] paramNames = getMethodParamNames(CallMethod.class,"sunvins1",null);
		for(int i=0;i<paramNames.length;i++){
			System.out.println(paramNames[i]);
		}*/
		String[] paramNames1 = getMethodParamNames(CallMethod.class,"sunvins1");
		for(int i=0;i<paramNames1.length;i++){
			System.out.println(paramNames1[i]);
		}
	}
	 /**
     * 
     * <p>
     * 获取方法参数名称
     * </p>
     * 
     * @param cm
     * @return
     */
    protected static String[] getMethodParamNames(CtMethod cm) {
        CtClass cc = cm.getDeclaringClass();
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                .getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            try {
				throw new Exception(cc.getName());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
 
        String[] paramNames = null;
        try {
            paramNames = new String[cm.getParameterTypes().length];
        } catch (NotFoundException e) {
           e.printStackTrace();
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++) {
            paramNames[i] = attr.variableName(i + pos);
        }
        return paramNames;
    }
 
    /**
     * 获取方法参数名称，按给定的参数类型匹配方法(用于查看是否存在某方法)
     * 
     * @param clazz
     * @param method
     * @param paramTypes
     * @return
     */
    public static String[] getMethodParamNames(Class<?> clazz, String method,
            Class<?>... paramTypes) {
 
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;
        CtMethod cm = null;
        try {
            cc = pool.get(clazz.getName());
            if(paramTypes!=null){
	            String[] paramTypeNames = new String[paramTypes.length];
	            for (int i = 0; i < paramTypes.length; i++){
	                paramTypeNames[i] = paramTypes[i].getName();
	            }
	            cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));
            }
            
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return getMethodParamNames(cm);
    }
 
    /**
     * 获取方法参数名称，匹配同名的某一个方法
     * 
     * @param clazz
     * @param method
     * @return
     * @throws NotFoundException
     *             如果类或者方法不存在
     * @throws MissingLVException
     *             如果最终编译的class文件不包含局部变量表信息
     */
    public static String[] getMethodParamNames(Class<?> clazz, String method) {
 
        ClassPool pool = ClassPool.getDefault();
        CtClass cc;
        CtMethod cm = null;
        try {
            cc = pool.get(clazz.getName());
            cm = cc.getDeclaredMethod(method);
        } catch (NotFoundException e) {
           e.printStackTrace();
        }
        return getMethodParamNames(cm);
    }
    @SuppressWarnings({ "rawtypes", "unused" })
	public static Class[] getParamTypes(Class clazz,String methodName) {
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
}
