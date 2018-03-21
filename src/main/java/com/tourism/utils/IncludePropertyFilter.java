package com.tourism.utils;

import java.util.HashSet;
import java.util.Set;

import net.sf.json.util.PropertyFilter;

/**
 * <Strong>Description�?</Strong>包含字段过滤�?
 * <p>
 * <Strong>Date�?</Strong> 2016�?11�?7�?
 *
 * @author LiRixin
 * @since JDK 1.7
 */
public class IncludePropertyFilter implements PropertyFilter {

	private Set<String> includes = new HashSet<String>();

	public IncludePropertyFilter(String... includes) {
		if (includes != null) {
			for (String include : includes) {
				this.includes.add(include);
			}
		}
	}

	@Override
	public boolean apply(Object source, String name, Object value) {
		if (includes.contains(name)) {
			return false;
		}
		return true;
	}

}
