package com.wzf.xml_parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;

import com.wzf.constrant.Common;

/**
 * @author Administrator
 * 所有解析的开始处
 * 解析顺序
 * 1.解析所有文件的include标签
 * 2.解析所有过滤器有关的标签
 * 	 2.1:需要先解析所有的实际的filter标签
 *   2.2:解析标签列表
 * 3.解析所有控制器有关的标签
 * 4.解析视图有关的标签
 */
public class ParseEntry extends BaseParse {

	private HashMap<String, Document> filePathMap = new HashMap<String, Document>();

	private FilterParse filterParse = new FilterParse();

	private ControllerParse controllerParse = new ControllerParse();

	private IncludeParse includeParse = new IncludeParse();

	/**
	 * 如果解析的是单个文件，可以直接使用
	 * @param filePath
	 */
	public void startParse(String filePath) {
		includeParse.startParse(filePath);
		filePathMap = includeParse.getFilePathMap();

		parseAllFilterTag();
		parseAllControllerTag();
	}

	@Override
	protected List<Element> parse(Document document) {
		return null;
	}

	@Override
	public void print() {
		includeParse.print();
		filterParse.print();
		controllerParse.print();
	}

	/**
	 * 解析所有过滤器标签
	 */
	private void parseAllFilterTag() {

		// 解析实际的filter标签
		for (Entry<String, Document> fileEntry : filePathMap.entrySet()) {
			Document document = fileEntry.getValue();
			List<Element> filter_root_tag_list = filterParse.parse(document);
			filterParse.parseAllFilterTag(filter_root_tag_list);
		}

		// 解析filter-list标签
		for (Entry<String, Document> fileEntry : filePathMap.entrySet()) {
			Document document = fileEntry.getValue();
			List<Element> filter_root_tag_list = filterParse.parse(document);
			filterParse.parseAllFilterListTag(filter_root_tag_list);
		}
	}

	/**
	 * 解析所有控制器标签
	 */
	private void parseAllControllerTag() {
		for (Entry<String, Document> fileEntry : filePathMap.entrySet()) {
			Document document = fileEntry.getValue();
			controllerParse.parse(document);
		}
	}

	
	
	public FilterParse getFilterParse() {
		return filterParse;
	}

	public ControllerParse getControllerParse() {
		return controllerParse;
	}

	public static void main(String[] args) {
		ParseEntry entry = new ParseEntry();
		entry.startParse(Common.configFilePath);
		entry.print();
	}

}
