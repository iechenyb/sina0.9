package com.cyb.spring;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
/**
 * 模拟spring，加载配置文件，执行ioc和aop。
 * @author DHUser
 *
 */
public class MyApplicationContext {
	List<BeanInformation> beansInformation = new ArrayList<BeanInformation>();
    Map<String,Object> singleton = new HashMap<String,Object>();
    
    
    public MyApplicationContext(){};
    public MyApplicationContext(String filename){
	   	 readXml(filename);//读xml
	   	 initBean();//初始化bean
	   	 this.injectObject();//注入bean
    }
    @SuppressWarnings("unchecked")
	public void readXml(String filename){
   	 SAXReader saxReader = new SAXReader();
   	 Document document = null;
   	 try{
       	 //使用反射机制,通过文件名加载文件路径
   		 URL xmlPath = this.getClass().getClassLoader().getResource(filename);
   		 
   		 //通过文件路径获得这个文件的document对象
   		 document = saxReader.read(xmlPath);
   		 
   		 Map<String,String> nsMap = new HashMap<String,String>();
	         nsMap.put("ns","http://www.springframework.org/schema/beans");//加入命名空间
	         
	         XPath xsub = document.createXPath("//ns:beans/ns:bean");//创建beans/bean查询路径
	         xsub.setNamespaceURIs(nsMap);//设置命名空间
   	
   		 //获得所有路径下的节点
   		 List<Element> beans = xsub.selectNodes(document);//获取文档下所有bean节点
   		 for(Element element : beans){
   			 System.out.println(element.attributeValue("id"));
   			 System.out.println(element.attributeValue("class"));
   			 
   			 BeanInformation beanInformation = new BeanInformation();
   			 beanInformation.setId(element.attributeValue("id"));
   			 beanInformation.setName(element.attributeValue("class"));
   			 
   			 XPath xref = element.createXPath("ns:property");//创建properties查询路径
   			 xref.setNamespaceURIs(nsMap);//设置命名空间
   			 //获取属性信息
   			 List<Element> propertys = xref.selectNodes(element);
   			 for(Element property : propertys){
   				 PropertyInformation propertyInformation = new PropertyInformation();
   				 propertyInformation.setName(property.attributeValue("name"));
   				 propertyInformation.setRef(property.attributeValue("ref"));
   				 propertyInformation.setValue(property.attributeValue("value"));
   				 beanInformation.getPropertyInformation().add(propertyInformation);
   			 }
   			 beansInformation.add(beanInformation);
   		 }
        } catch(Exception e){
       	 e.printStackTrace();
        }
    }
    
    public void initBean(){
   	 for(BeanInformation beanInformation: beansInformation){
   		 if(beanInformation.getName()!=null && !"".equals(beanInformation.getName())){
   			 //通过反射机制，根据名字初始化这个类
   			try {
					singleton.put(beanInformation.getId(), Class.forName(beanInformation.getName()).newInstance());
				} catch (Exception e) {
					e.printStackTrace();
				}
   		 }
   	 }
    }
    
    /**
	 *    关于注入的实现
	 */
	private void injectObject() {
		for(BeanInformation beanInformation : beansInformation){
			Object bean = singleton.get(beanInformation.getId());
			if(bean!=null){
				try {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
					for(PropertyInformation propertyInformation : beanInformation.getPropertyInformation()){//spring中所有的属性
						for(PropertyDescriptor properdesc : ps){//用户自定义bean的每个属性的信息
							if(propertyInformation.getName().equals(properdesc.getName())){
								Method setter = properdesc.getWriteMethod();//获取属性的setter方法 ,private
								if(setter!=null){
									Object value = null;
									if(propertyInformation.getRef()!=null && !"".equals(propertyInformation.getRef().trim())){
										value = singleton.get(propertyInformation.getRef());//根据id获取已经创建好的bean
									}else{
										//null,interface com.cyb.spring.Axe
										//value = "";设置普通属性
										value = ConvertUtils.convert(propertyInformation.getValue(), properdesc.getPropertyType());
									}
									setter.setAccessible(true);
									setter.invoke(bean, value);//把引用对象注入到属性 调用set方法
								}
								break;
							}
						}
					}
				} catch (Exception e) {
				}
			}
		}
	}
	
    public Object getBean(String id){
		 return this.singleton.get(id); 	 
    }
}
