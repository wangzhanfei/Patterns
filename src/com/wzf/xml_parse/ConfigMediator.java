package com.wzf.xml_parse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;

import com.wzf.constrant.Common;
import com.wzf.context.ConfigContext;
import com.wzf.datatype.ActionInfo;
import com.wzf.datatype.ControllerInfo;

public class ConfigMediator extends BaseParse {

	// 解析入口
	private ParseEntry parseEntry = new ParseEntry();

	public ConfigMediator() {

	}

	public void startParse(String filePath) {

		parseEntry.startParse(filePath);

		mergeData();

		assignData();
	}

	/**
	 * @param filePath  配置文件最顶层文件
	 * @param clearRef  true加载完后清除应用
	 */
	public void startParse(String filePath, boolean clearRef) {

		startParse(filePath);
		if (clearRef) {
			clearData();
		}
	}

	private void assignData() {

		ConfigContext.setFilterListMap(parseEntry.getFilterParse()
				.getFilterMapList());
		// ConfigContext.setFilterMap(parseEntry.getFilterParse().getFilterMap());
		ConfigContext.setApplicationControllerMapper(parseEntry
				.getControllerParse().getControllerTagListMap());
	}

	@Override
	protected List<Element> parse(Document document) {
		return null;
	}

	/**
	 * 取消引用
	 */
	public void clearData() {
		parseEntry = null;
	}

	private void mergeData() {
		HashMap<String, List<Class<?>>> clsListMap = parseEntry
				.getFilterParse().getFilterMapList();
		HashMap<String, Class<?>> clsMap = parseEntry.getFilterParse()
				.getFilterMap();

		HashMap<String, HashMap<String, ControllerInfo>> platformMaps = parseEntry
				.getControllerParse().getControllerTagListMap();

		for (Entry<String, HashMap<String, ControllerInfo>> entry : platformMaps
				.entrySet()) {

			String platformKey = entry.getKey();
			HashMap<String, ControllerInfo> concreatePlatformMap = entry
					.getValue();

			for (Entry<String, ControllerInfo> controllerEntry : concreatePlatformMap
					.entrySet()) {

				String controllerName = controllerEntry.getKey();
				ControllerInfo controllerInfo = controllerEntry.getValue();

				HashMap<String, ActionInfo> actionMapper = controllerInfo
						.getActionMapper();

				for (Entry<String, ActionInfo> actionEntry : actionMapper
						.entrySet()) {
					String actinoName = actionEntry.getKey();
					ActionInfo actionInfo = actionEntry.getValue();

					List<String> filterRefList = actionInfo.getFilterRefList();

					List<Class<?>> allClsList = new ArrayList<Class<?>>();
					for (String name : filterRefList) {
						List<Class<?>> clsList = clsListMap.get(name);
						if (clsList != null) {
							allClsList.addAll(clsList);
						}
						Class<?> cls = clsMap.get(name);
						if (cls != null) {
							allClsList.add(cls);
						}
					}
					actionInfo.setClsList(allClsList);
					// actionInfo.setFilterRefList(null);
				}
			}

		}
	}

	public void print() {
		parseEntry.print();
	}

	public static long getTime() {
		return new Date().getTime();
	}

	public static void main(String[] args) {
		classTest();
	}

	private static void classTest() {
		long startTime = getTime();
		ConfigMediator mediator = new ConfigMediator();
		mediator.startParse(Common.configFilePath);
		mediator.print();
		mediator.clearData();
		long endTime = getTime();
		System.out.println("耗时:" + (endTime - startTime));
	}

}
