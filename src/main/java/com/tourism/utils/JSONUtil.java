package com.tourism.utils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JSONUtil {
    /**
     * 设置javabean中日期转换时的格式
     */
	public static JsonConfig jsonDateConfig = new JsonConfig();
	
	
	/**
	 * 在对象中新加入一个key和value
	 * @param obj 对象
	 * @param key
	 * @param value
	 * @return 字符串的新对象
	 */
	public static String makeJsonBeanByKey(Object obj,String key,String value){
		JSONObject jsonObject=JSONObject.fromObject(obj);
		jsonObject.put(key, value);
		return jsonObject.toString();		
	}
	public static String makeJsonBeanByKey(String obj, String key,List<String> value) {
		JSONObject jsonObject=JSONObject.fromObject(obj);
		jsonObject.put(key, value);
		return jsonObject.toString();
	}

	/**
	 * 将集合的对象转换成JSONArray字符串
	 * @param list 集合对象
	 * @return JSONArray字符串 
	 */
	public static String changeJsonArray(List list){
		JSONArray jsonArray=JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * 将对象转换成JSONObject字符串
	 * @param object 对象
	 * @return jsnoObject字符串 
	 */
	public static String changeJsonObject(Object object){
		JSONObject jsnoObject=JSONObject.fromObject(object);
		return jsnoObject.toString();
	}
	
	
	/**
	 * <Strong>Description：</Strong>实体对象转json
	 *
	 * @param bean
	 *            实体对象
	 * @param includes
	 *            需要包含的字段
	 * @return
	 * @author LiRixin
	 */
	public static <T> JSONObject toInJson(T bean, String... includes) {
		return JSONObject.fromObject(bean, setInCludes(includes));
	}
	
	private static JsonConfig setInCludes(final String... includes) {
		final JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "handler", "hibernateLazyInitializer" });
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		jsonConfig.registerDefaultValueProcessor(String.class, new DefaultStringValueProcessor());
		jsonConfig.setJsonPropertyFilter(new IncludePropertyFilter(includes));
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		return jsonConfig;
	}
	
	/**
	 * <Strong>Description：</Strong>集合转json
	 *
	 * @param collection
	 *            集合
	 * @param excludes
	 *            需要包含的字段
	 * @return
	 * @author LiRixin
	 */
	public static JSONArray toInJson(Collection<?> collection, String... includes) {
		return JSONArray.fromObject(collection, setInCludes(includes));
	}

}
