package com.wzf.xml_parse;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.wzf.datatype.ActionInfo;
import com.wzf.datatype.ControllerInfo;

public class ConfigMediator extends BaseParse {

	private ControllerParse controllerParse;

	private FilterParse filterParse;

	public ConfigMediator(String filePath) {

		Document document = null;
		try {
			document = getDocument(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		filterParse = new FilterParse(document);

		controllerParse = new ControllerParse(document);
	}

	@Override
	public void startParse() {

		filterParse.startParse();
		controllerParse.startParse();

		mergeData();

		ConfigContext.setFilterListMap(filterParse.getFilterMapList());
		ConfigContext.setFilterMap(filterParse.getFilterMap());
		ConfigContext.setPlatformControllerMap(controllerParse
				.getControllerTagListMap());
		
	}
	
	/**
	 * 取消引用
	 */
	private void clearData(){
		filterParse=null;
		controllerParse=null;
	}
	
	private void mergeData() {
		HashMap<String, List<Class<?>>> clsListMap = filterParse
				.getFilterMapList();

		HashMap<String, HashMap<String, ControllerInfo>> platformMaps = controllerParse
				.getControllerTagListMap();

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

					for (String name : filterRefList) {
						List<Class<?>> clsList = clsListMap.get(name);
						actionInfo.setClsList(clsList);
						actionInfo.setFilterRefList(null);
					}
				}
			}

		}
	}

	public void print() {
		controllerParse.print();
	}

	public static long getTime() {
		return new Date().getTime();
	}

	public static void main(String[] args) {
		long startTime = getTime();
		String filePath = "E:\\MyEclipse\\Patterns\\config\\filter-config.xml";
		ConfigMediator mediator = new ConfigMediator(filePath);
		mediator.startParse();
		mediator.print();
		mediator.clearData();
		long endTime = getTime();

		System.out.println("耗时:" + (endTime - startTime));
	}

}
