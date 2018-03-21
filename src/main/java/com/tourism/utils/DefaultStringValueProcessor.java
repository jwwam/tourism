package com.tourism.utils;

import net.sf.json.processors.DefaultValueProcessor;

/**
 * <Strong>Description�?</Strong>json默认空字符串规则
 * <p>
 * <Strong>Date�?</Strong> 2016�?11�?7�?
 *
 * @author LiRixin
 * @since JDK 1.7
 */
public class DefaultStringValueProcessor implements DefaultValueProcessor {
	@Override
	public Object getDefaultValue(@SuppressWarnings("rawtypes") Class type) {
		return "null";
	}
}