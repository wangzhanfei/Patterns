package com.wzf.xml_parse;

import java.util.HashMap;
import java.util.List;

import com.wzf.datatype.ControllerInfo;

public class ConfigContext {

	/**
	 * 单个过滤器缓存  1对1
	 */
	private static HashMap<String, Class<?>> filterMap;

	/**
	 * 一组过滤器缓存 1对多
	 */
	private static HashMap<String, List<Class<?>>> filterListMap = new HashMap<String, List<Class<?>>>();

	/**
	 * 平台控制信息   平台=>一组控制器信息,1对多
	 */
	private static HashMap<String, HashMap<String, ControllerInfo>> platformControllerMap = new HashMap<String, HashMap<String, ControllerInfo>>();

	public static HashMap<String, Class<?>> getFilterMap() {
		return filterMap;
	}

	public static void setFilterMap(HashMap<String, Class<?>> filterMap) {
		ConfigContext.filterMap = filterMap;
	}

	public static HashMap<String, List<Class<?>>> getFilterListMap() {
		return filterListMap;
	}

	public static void setFilterListMap(
			HashMap<String, List<Class<?>>> filterListMap) {
		ConfigContext.filterListMap = filterListMap;
	}

	public static HashMap<String, HashMap<String, ControllerInfo>> getPlatformControllerMap() {
		return platformControllerMap;
	}

	public static void setPlatformControllerMap(
			HashMap<String, HashMap<String, ControllerInfo>> platformControllerMap) {
		ConfigContext.platformControllerMap = platformControllerMap;
	}

	
	
}
