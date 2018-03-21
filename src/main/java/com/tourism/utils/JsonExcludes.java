package com.tourism.utils;

import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonExcludes {
	
	public static <T> String excludes(T t,String ... excludesName){
		
		StringBuffer sb =  new StringBuffer();
		for (int i = 0; i < excludesName.length; i++) {
			if( i == excludesName.length-1 ){
				sb.append(excludesName[i]);
			}else{
				sb.append(excludesName[i]+",");
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
		config.setExcludes(new String[]{ sb.toString() });//除去字段属性
		return JSONObject.fromObject(t, config).toString();
		
	}

}
