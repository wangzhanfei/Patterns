package com.wzf.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.wzf.datatype.ControllerInfo;
import com.wzf.filter.ActionFilter;

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
	private static HashMap<String, HashMap<String, ControllerInfo>> applicationControllerMapper = new HashMap<String, HashMap<String, ControllerInfo>>();

	public static void print() {
		for (Entry<String, List<Class<?>>> iterable_element : filterListMap
				.entrySet()) {
			List<Class<?>> list = iterable_element.getValue();

			System.out.println(iterable_element.getKey());
			for (Class<?> class1 : list) {
				System.out.println("         " + class1.getName());
			}
		}
	}

	public static void setFilterMap(HashMap<String, Class<?>> filterMap) {
		ConfigContext.filterMap = filterMap;
	}

	public static void setFilterListMap(
			HashMap<String, List<Class<?>>> filterListMap) {
		ConfigContext.filterListMap = filterListMap;
	}

	public static HashMap<String, Class<?>> getFilterMap() {
		return filterMap;
	}

	public static HashMap<String, List<Class<?>>> getFilterListMap() {
		return filterListMap;
	}

	public static HashMap<String, HashMap<String, ControllerInfo>> getApplicationControllerMapper() {
		return applicationControllerMapper;
	}

	public static void setApplicationControllerMapper(
			HashMap<String, HashMap<String, ControllerInfo>> applicationControllerMapper) {
		ConfigContext.applicationControllerMapper = applicationControllerMapper;
	}

	public static List<ActionFilter> getActionFilterList(String actionName) {
		List<Class<?>> clsList = ConfigContext.getFilterListMap().get(
				actionName);
		for (Class<?> class1 : clsList) {
			System.out.println(class1.getName());
		}
		List<ActionFilter> actionFilters = new ArrayList<ActionFilter>();
		if (clsList == null || clsList.size() == 0) {
			return actionFilters;
		}
		for (Class<?> actionCls : clsList) {
			try {
				ActionFilter actionFilter = (ActionFilter) actionCls
						.newInstance();
				actionFilters.add(actionFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return actionFilters;
	}

	/**
	 * 获取平台控制器信息
	 * @param platformName
	 * @return
	 */
	public static HashMap<String, ControllerInfo> getPlatformControllerInfo(
			String platformName) {
		return applicationControllerMapper.get(platformName);
	}
}
