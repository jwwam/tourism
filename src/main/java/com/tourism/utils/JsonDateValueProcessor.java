package com.tourism.utils;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * <Strong>Description�?</Strong>自定义日期类型转�?
 * <p>
 * <Strong>Date�?</Strong> 2016�?11�?7�?
 *
 * @author LiRixin
 * @since JDK 1.7
 */
public class JsonDateValueProcessor implements JsonValueProcessor {
	@Override
	public Object processArrayValue(final Object value, final JsonConfig jsonConfig) {
		return value == null ? null : ((Date) value).getTime();
	}

	@Override
	public Object processObjectValue(final String key, final Object value, final JsonConfig jsonConfig) {
		return value == null ? null : ((Date) value).getTime();
	}
}
