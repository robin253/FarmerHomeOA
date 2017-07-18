package com.huike.base.tools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.smartframework.common.utils.ExStringUtils;

public class ListUtil {
	public static List<Integer> handleString2Array(String array, String regex) {
		List<Integer> list = new ArrayList<Integer>();
		String[] strings = StringUtils.split(array, regex);
		for (int i = 0; i < strings.length; i++){
			list.add(ExStringUtils.getIntValue(strings[i]));
		}
		return list;
	}
	
	public static List<String> handleString2StringArray(String array, String regex) {
		List<String> list = new ArrayList<String>();
		String[] strings = StringUtils.split(array, regex);
		for (int i = 0; i < strings.length; i++){
			list.add(strings[i]);
		}
		return list;
	}
}
