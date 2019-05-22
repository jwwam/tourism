package com.tourism.utils;

public class CheckUtil {

	
	/**
	 * @param temp
	 * @return 判断是否字符串
	 */
	public static boolean judgeType(Object temp){
		
		if(temp instanceof String){
			return true;
		}else{
			return false;
		} 
	}
	
	
	/**
	 * @param temp
	 * @return 判断是整形
	 * 成功，返回true， 是吧，返回false
	 */
	public static boolean checkInt(String temp){
		try {
			Integer.parseInt(temp);
			return true;
		} catch (Exception e) {
			return false;
		}
			
	}	
}
