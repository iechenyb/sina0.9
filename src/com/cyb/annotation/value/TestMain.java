package com.cyb.annotation.value;

import java.lang.reflect.Field;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestMain {
	public static void main(String[] args) {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;
		Field[] fields = CompanyBean.class.getDeclaredFields();
		for (Field f : fields) {
			jsonObject = new JSONObject();
			Class cls = f.getType();
			System.out.println(f.getAnnotatedType());
			Hidden hidden = f.getAnnotation(Hidden.class);//获取指定的注解
			if (hidden != null) {
				boolean isHidden = hidden.value();
				jsonObject.put("hidden", isHidden);
			}
			Header header = f.getAnnotation(Header.class);
			System.out.println(header);

			if (header != null) {
				jsonObject.put("header", header.value());
			}
			jsonObject.put("name", f.getName());
			System.out.println(cls.getSimpleName());
			if (!"String".equals(cls.getSimpleName()))
				jsonObject.put("type", cls.getSimpleName());
			
			if ("Date".equals(cls.getSimpleName())) {
				jsonObject.put("type", "date");
				jsonObject.put("dateFormat", "Y-m-d");
			}
			jsonArray.add(jsonObject);
		}
		System.out.println(jsonArray);
	}
}
