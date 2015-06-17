package com.wzf.xml_parse;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

public class FilterParse extends BaseParse {

	/**
	 * 每个filter的缓存位置,key值时 filter的属性name,表示filter_attr_name
	 * value是将类的路径转为"类"的类型
	 */
	private HashMap<String, Class<?>> filterMap = new HashMap<String, Class<?>>();

	/**
	 * key值是filter-list的属性name 表示filter_list_attr_name
	 * value值是一组"类"的类型
	 */
	private HashMap<String, List<Class<?>>> filterMapList = new HashMap<String, List<Class<?>>>();

	/**
	 * 每个filter-list子节点filter-ref缓存的位置,key值是  filter-list的属性name,表示为filter_list_attr_name
	 */
	private HashMap<String, List<Element>> filter_ref_tag_MapList = new HashMap<String, List<Element>>();

	public FilterParse() {

	}
	
	public FilterParse(Document document) {
		this.document=document;
	}
	

	public void print() {
		for (Entry<String, Class<?>> entry : filterMap.entrySet()) {
			String name = entry.getKey();
			String path = entry.getValue().getName();
			System.out.println(name + "   " + path);
		}
		System.out.println("过滤器链...................");
		for (Entry<String, List<Class<?>>> entry : filterMapList.entrySet()) {
			String name = entry.getKey();
			List<Class<?>> pathList = entry.getValue();
			System.out.println("---------------------------------------------");
			for (Class<?> class1 : pathList) {
				System.out.println(name + "    " + class1.getName());
			}
		}
	}

	@Override
	public void startParse() {
		try {
			Document document = getDocument();
			parse(document);
		} catch (DocumentException e) {
			// throwException(null, "没有发现配置文件");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解析filter-root
	 * @param document
	 */
	private void parse(Document document) {
		Element rootNode = document.getRootElement();

		List<Element> filter_root_tag_list = getElementList(rootNode,
				"filter-root");
		parseFilterRoot(filter_root_tag_list);
	}

	/**
	 *  解析filter和filter-list
	 * @param filter_root_tag_list
	 */
	private void parseFilterRoot(List<Element> filter_root_tag_list) {

		for (Element element : filter_root_tag_list) {

			List<Element> filter_tag_list = getElementList(element, "filter");
			paseFilterTag(filter_tag_list);

			List<Element> filter_list_tag_list = getElementList(element,
					"filter-list");
			paseFilterListTag(filter_list_tag_list);

		}
	}

	/**
	 * 解析标签filter
	 * @param filter_tag_list
	 */
	private void paseFilterTag(List<Element> filter_tag_list) {

		for (Element filter : filter_tag_list) {
			String name = getAttribute(filter, "name");
			throwException(name, "过滤器没有填写名字");

			if (!isEmpty(filterMap, name)) {
				throwException(null, "filter name:" + name + "：重复输入");
			}

			String clsName = getAttribute(filter, "class");
			throwException(name, "过滤器没有填写类名");

			Class<?> cls = null;
			try {
				cls = Class.forName(clsName);
			} catch (ClassNotFoundException e) {
				throwException(cls, name + "   没有发现类");
			}

			filterMap.put(name, cls);
		}
	}

	/**
	 * 解析标签filter-list
	 * @param filter_list_tag_list，多组filter-list标签
	 */
	private void paseFilterListTag(List<Element> filter_list_tag_list) {

		// 1.把filter-list标签的属性name作为key
		// 2.把filter-list标签下的filter-ref标签的属性name作为value
		// 3.如果（filter-ref属性引用的是一个filter-list标签那么将继续解析，知道引用的是filter标签）
		// 4.在3的过程中不能重复引用已经出现过的filter-list标签（不能名字重复）,不能重复是对于单个filter-list标签列表
		HashMap<String, List<String>> filter_list_tag_list_map = new HashMap<String, List<String>>();

		
		//加载所有filter-list标签
		for (Element filter_list_tag : filter_list_tag_list) {

			String name = getAttribute(filter_list_tag, "name");
			throwException(name, "过滤器没有填写名字");

			if (!isEmpty(filterMapList, name)) {
				throwException(null, "filter-list name:" + name + "：重复输入");
			}

			List<Element> filter_ref_tag_list = getElementList(filter_list_tag,
					"filter-ref");
			filter_ref_tag_MapList.put(name, filter_ref_tag_list);

			Assert.assertNotNull("filter_ref_tag_list is null",
					filter_ref_tag_list);
		}

		for (Element filter_list_tag : filter_list_tag_list) {

			String name = getAttribute(filter_list_tag, "name");
		
		
			List<Element> filter_ref_tag_list = filter_ref_tag_MapList
					.get(name);
			Assert.assertNotNull("filter_ref_tag_list is null",
					filter_ref_tag_list);

			// 记录filter-list标签下filter-ref标签引用的过滤器名字(不包含filter-list标签的名字)
			List<String> filterTagNames = new ArrayList<String>();

			// 记录已有的filter-list标签的名字
			List<String> allParentNames = new ArrayList<String>();
			allParentNames.add(name);
			paseFilterRefTag(filter_ref_tag_list, allParentNames,
					filterTagNames);
			
			filter_list_tag_list_map.put(name, filterTagNames);
		}

		for (Entry<String, List<String>> entry : filter_list_tag_list_map
				.entrySet()) {
			String filterRefName = entry.getKey();
			List<String> clsRefList = entry.getValue();

			List<Class<?>> clsList = new ArrayList<Class<?>>();
			for (String name : clsRefList) {
				Class<?> cls = filterMap.get(name);
				clsList.add(cls);
			}

			filterMapList.put(filterRefName, clsList);
		}
	}

	/**
	 * 解析filter-ref标签,如果引用了另一个filter-list标签将继续解析
	 * @param filter_ref_tag_list
	 * @param allParentNames
	 * @param filterTagNames
	 */
	private void paseFilterRefTag(List<Element> filter_ref_tag_list,
			List<String> allParentNames, List<String> filterTagNames) {

		for (Element filterRef : filter_ref_tag_list) {

			String name = getAttribute(filterRef, "name");
			throwException(name, "filter-ref 没有填写名字");

			// 防止因为filter-list重复引用导致递归递归
			for (String parentName : allParentNames) {
				if (parentName.equals(name)) {
					throwException(null,
							"filter-ref 不能在filter-list标签下重复引用上级的父标签   name="
									+ name);
				}
			}

			// 0：不存在，1在filterMap里面，2：在filterMapList里面
			int nameExistState = 0;

			if (isEmpty(filterMap, name)) {
				nameExistState = 0;
			} else {
				nameExistState = 1;
			}

			if (nameExistState == 0) {
				if (isEmpty(filter_ref_tag_MapList, name)) {
					nameExistState = 0;
				} else {
					nameExistState = 2;
				}
			}

			if (nameExistState == 0) {
				throwException(null, "filter-ref  引用" + name + ",不存在");
			}

			switch (nameExistState) {
			case 1:
				filterTagNames.add(name);
				break;
			case 2:
				List<Element> _filter_ref_tag_list = filter_ref_tag_MapList
						.get(name);
				
				allParentNames.add(name);
				paseFilterRefTag(_filter_ref_tag_list, allParentNames,
						filterTagNames);
				break;
			}

		}
	}

	@SuppressWarnings("rawtypes")
	private boolean isEmpty(HashMap map, String key) {
		if (map.containsKey(key)) {
			return false;
		} else {
			return true;
		}
	}

	
	
	public HashMap<String, Class<?>> getFilterMap() {
		return filterMap;
	}

	public HashMap<String, List<Class<?>>> getFilterMapList() {
		return filterMapList;
	}

	public HashMap<String, List<Element>> getFilter_ref_tag_MapList() {
		return filter_ref_tag_MapList;
	}

	public static void main(String[] args) {
		FilterParse filterParse = new FilterParse();
		filterParse.startParse();
		filterParse.print();
	}
}
