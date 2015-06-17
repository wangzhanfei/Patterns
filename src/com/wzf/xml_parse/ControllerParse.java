package com.wzf.xml_parse;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.wzf.datatype.ActionInfo;
import com.wzf.datatype.ControllerInfo;

public class ControllerParse extends BaseParse {

	private HashMap<String, HashMap<String, ControllerInfo>> controllerTagListMap = new HashMap<String, HashMap<String, ControllerInfo>>();

	public ControllerParse() {
	}
	
	public ControllerParse(Document document) {
		this.document=document;
	}
	
	@Override
	public void startParse() {
		try {
			Document document = getDocument();
			parse(document);
		} catch (DocumentException e) {
			throwException(null, "没有发现配置文件");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void print() {

		for (Entry<String, HashMap<String, ControllerInfo>> platform : controllerTagListMap
				.entrySet()) {

			String name = platform.getKey();
			HashMap<String, ControllerInfo> map = platform.getValue();

			for (Entry<String, ControllerInfo> controllerList : map.entrySet()) {
				String name1 = controllerList.getKey();
				ControllerInfo controllerInfo = controllerList.getValue();
				System.out.println(name + "    " + name1 + "    "
						+ controllerInfo.toString());
			}
		}

	}

	private void parse(Document document) {

		Element rootNode = document.getRootElement();
		List<Element> controller_root_tag = getElementList(rootNode,
				"controller-root");
		Assert.assertNotNull("controller_root_tag is null", controller_root_tag);

		parseControllerRootTag(controller_root_tag);
	}

	/**
	 * 解析controller-root标签
	 * @param controller_root_tag
	 */
	private void parseControllerRootTag(List<Element> controller_root_tag) {

		for (Element controller_list_tag : controller_root_tag) {

			List<Element> controller_list_tag_list = getElementList(
					controller_list_tag, "controller-list");
			parseControllerListTag(controller_list_tag_list);
		}

	}

	/**
	 * 解析controller-list标签
	 * @param controller_list_tag_list
	 */
	private void parseControllerListTag(List<Element> controller_list_tag_list) {

		for (Element controller_list_tag : controller_list_tag_list) {

			String identifier = getAttribute(controller_list_tag, "identifier");

			List<Element> controller_tag_list = getElementList(
					controller_list_tag, "controller");

			HashMap<String, ControllerInfo> controllerInfoMap = parseControllerTag(controller_tag_list);

			controllerTagListMap.put(identifier, controllerInfoMap);
		}
	}

	/**
	 * 解析controller标签
	 * @param controller_tag_list
	 * @return 
	 */
	private HashMap<String, ControllerInfo> parseControllerTag(
			List<Element> controller_tag_list) {

		HashMap<String, ControllerInfo> controllerInfoMap = new HashMap<String, ControllerInfo>();

		for (Element controller_tag : controller_tag_list) {

			ControllerInfo controllerInfo = new ControllerInfo();

			String controllerName = getAttribute(controller_tag, "name");
			String controllerClass = getAttribute(controller_tag, "class");

			Class<?> cls = null;
			try {
				cls = Class.forName(controllerClass);
				controllerInfo.setCls(cls);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throwException(null, controllerClass + " 不存在");
			}

			List<Element> action_tag_list = getElementList(controller_tag,
					"action");
			HashMap<String, ActionInfo> actionMapper = parseActionTag(
					action_tag_list, cls);

			controllerInfo.setActionMapper(actionMapper);
			
			controllerInfoMap.put(controllerName, controllerInfo);
		}

		return controllerInfoMap;
	}

	/**
	 * 解析action标签
	 * @param action_tag_list
	 * @param cls 
	 * @return 
	 */
	private HashMap<String, ActionInfo> parseActionTag(
			List<Element> action_tag_list, Class<?> cls) {
		HashMap<String, ActionInfo> actionMapper = new HashMap<String, ActionInfo>();

		for (Element action_tag : action_tag_list) {

			ActionInfo actionInfo = new ActionInfo();

			String actionName = getAttribute(action_tag, "name");
			String methodName = getAttribute(action_tag, "method");

			Method method = null;
			try {
				method = cls.getMethod(methodName);
				actionInfo.setMethod(method);
				actionInfo.setActionName(actionName);
			} catch (Exception e) {
				e.printStackTrace();
				throwException(null, "方法  " + methodName + " 不存在");
			}

			List<Element> filter_ref_tag_list = getElementList(action_tag,
					"filter-ref");
			List<String> filterRefList = parseFilterRef(filter_ref_tag_list);
			
			actionInfo.setFilterRefList(filterRefList);

			actionMapper.put(actionName, actionInfo);
		}
		return actionMapper;
	}

	private List<String> parseFilterRef(List<Element> filter_ref_tag_list) {
		
		List<String> list = new ArrayList<String>();

		for (Element filter_reg_tag : filter_ref_tag_list) {

			String name = getAttribute(filter_reg_tag, "name");
			list.add(name);
		}

		return list;
	}
	
	
	public HashMap<String, HashMap<String, ControllerInfo>> getControllerTagListMap() {
		return controllerTagListMap;
	}

	public static void main(String[] args) {
		ControllerParse parse=new ControllerParse();
		parse.startParse();
		parse.print();
	}

}






















