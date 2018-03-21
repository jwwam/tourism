package com.tourism.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBeanUtil {
	
	public static <T> T getBean(Class<T> entity){
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		T bean =(T) ac.getBean(entity);
		return bean;
	} 

}
