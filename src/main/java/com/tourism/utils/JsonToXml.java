package com.tourism.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.ListIterator;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

public class JsonToXml {
		
	public static String generator(String strJson,String strRoot){
		JSONObject jsonObj;
		StringBuffer sb = new StringBuffer();
		try {
			jsonObj = JSONObject.fromObject(JSONSerializer.toJSON(strJson));
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
			sb.append(getXML(jsonObj,0));
			return sb.toString();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception "+e.getMessage());
			return null;
		}
	}
	
	private static void append(StringBuffer sb ,String strData){
		sb.append("\"");
		sb.append(strData);
		sb.append("\"");
	}
	
	private static String reservedReplace(String str){
		return str.replaceAll("&","&amp;").replaceAll("<","&lt;").replaceAll(">","&gt;");
	}
	
	private static String getXML(JSONObject jsonObj,int nDepth){
		String strXML = "";
		StringBuffer sb = new StringBuffer();
		Set set=jsonObj.entrySet();
		Iterator it=set.iterator();
        while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();
            if(hasChild(jsonObj,(String)m.getKey())){
            	if(hasAttribute(jsonObj,(String)m.getKey())){
            		sb.append("<");
	            	sb.append(m.getKey());
	            	sb.append(getAttribute(jsonObj,(String)m.getKey() ));
	            	sb.append(getXML(jsonObj.getJSONObject((String)m.getKey()),nDepth+1));
	            	sb.append("</");
	            	sb.append(m.getKey());
	            	sb.append(">\n");
            	}else{
	            	sb.append("<");
	            	sb.append(m.getKey());
	            	sb.append(">");
	            	sb.append(getXML(jsonObj.getJSONObject((String)m.getKey()),nDepth+1));
	            	sb.append("</");
	            	sb.append(m.getKey());
	            	sb.append(">\n");
            	}
            }else{
            	if(((String)m.getKey()).equals("CDATA")){
            		sb.append("<![CDATA[");
	            	sb.append(jsonObj.get(m.getKey()));
	            	sb.append("]]>");
            	}else if(((String)m.getKey()).substring(0,1).equals("@")){
            	}else if(((String)m.getKey()).substring(0,1).equals("#")){
            	}else{
	            	if(jsonObj.get(m.getKey()).getClass().equals(new JSONArray().getClass())){
	            		sb.append(getJsonArrayData((JSONArray)jsonObj.get(m.getKey()),(String)m.getKey()));
	            	}else{
	            		sb.append("<");
		            	sb.append(m.getKey());
		            	sb.append(">");
		            	//sb.append(reservedReplace((String)jsonObj.get(m.getKey())));
		            	sb.append(reservedReplace(String.valueOf(jsonObj.get(m.getKey()))));
		            	sb.append("</");
		            	sb.append(m.getKey());
		            	sb.append(">\n");
	            	}
            	}
            }
        }
		return sb.toString();
	}
	
	private static boolean hasChild(JSONObject jsonObj ,String key){
		try{
			jsonObj.getJSONObject(key);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	private static String getAttribute(JSONObject jsonObj,String key){
		return getAttribute(jsonObj.getJSONObject(key));
	}
	
	private static String getAttribute(JSONObject json){
		StringBuffer sb = new StringBuffer();
		Set set=json.entrySet();
		Iterator it=set.iterator();
		boolean hasText = false;
        while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();
            if(((String)m.getKey()).substring(0,1).equals("@")){
            	sb.append(" ");
        		sb.append(((String)m.getKey()).substring(1));
            	sb.append("=");
            	append(sb,reservedReplace(String.valueOf(json.get(m.getKey()))));
            	//append(sb,reservedReplace((String)json.get(m.getKey())));
            }if(((String)m.getKey()).substring(0,1).equals("#")){
            	sb.append(">");
            	sb.append(reservedReplace(String.valueOf(json.get(m.getKey()))));
            	//sb.append(reservedReplace((String)json.get(m.getKey())));
            	hasText = true;
            }
        }
        if(!hasText)sb.append(">");
        return sb.toString();
	}
	
	private static String getText(JSONObject jsonObj,String key){
		return getText(jsonObj.getJSONObject(key));
	}
	
	private static String getText(JSONObject jsonObj){
		StringBuffer sb = new StringBuffer();
		Set set=jsonObj.entrySet();
		Iterator it=set.iterator();
		String strData = "";
        while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();
            if(((String)m.getKey()).substring(0,1).equals("#")){
            	strData = reservedReplace(String.valueOf(jsonObj.get(m.getKey())));
            	//strData = reservedReplace((String)jsonObj.get(m.getKey()));
            }
        }
        sb.append(">");
        sb.append(strData);
        return sb.toString();
	}
	
	private static boolean hasAttribute(JSONObject jsonObj,String key){
		return hasAttribute(jsonObj.getJSONObject(key));
	}
	
	private static boolean hasAttribute(JSONObject json){
		Set set=json.entrySet();
		Iterator it=set.iterator();
        while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();
            if(((String)m.getKey()).substring(0,1).equals("@")) return true;
        }
        return false;
	}
	
	public static String getJsonArrayData(JSONArray jsonArray,String key){
		StringBuffer sb = new StringBuffer();
		ListIterator itr = jsonArray.listIterator();
		JSONObject jsonObj;
		while(itr.hasNext()){
			JSONObject obj = (JSONObject)itr.next();
			jsonObj = new JSONObject();
			jsonObj.put(key, obj);
			sb.append(getXML(jsonObj,0));
		}
		return sb.toString();
	}
	
}
