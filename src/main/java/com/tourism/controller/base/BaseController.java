package com.tourism.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;

import com.tourism.utils.StateParameter;

public abstract class BaseController{
	protected final String success=StateParameter.SUCCESS;
	protected final String  fail=StateParameter.FAULT;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());  

	public ModelMap getModelMap(String status,Object data,String msg){
		ModelMap modelMap=new ModelMap();
		modelMap.put("status", status);
		modelMap.put("data", data);
		modelMap.put("msg", msg);
		return modelMap;
		
	}
	
	
}
