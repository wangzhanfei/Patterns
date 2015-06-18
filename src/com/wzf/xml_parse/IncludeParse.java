package com.wzf.xml_parse;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;

import com.wzf.constrant.Common;
import com.wzf.utility.system.SystemUtils;


public class IncludeParse extends BaseParse {

	private HashMap<String, Document> filePathMap = new HashMap<String, Document>();

	private String filePath = "";

	/**
	 * 解析配置文件的入口
	 * @param filePath
	 */
	public void startParse(String filePath) {
		Document document = getDocument(filePath);
		this.filePath = filePath;
		filePathMap.put(filePath, document);
		parse(document);
	}

	@Override
	protected List<Element> parse(Document document) {
		Element rootNode = document.getRootElement();

		List<Element> include_tag_list = getElementList(rootNode, "include");
		parseIncludeTag(include_tag_list);

		// 解析完毕后将根文件取出，避免重复解析
		filePathMap.remove(filePath);
		return include_tag_list;
	}

	private void parseIncludeTag(List<Element> include_tag_list) {

		for (Element include_tag : include_tag_list) {
			String file = getAttribute(include_tag, "file");
			throwException(file, "include 没有填写文件路径,文件路径格式在项目根目录config文件夹下添加");

			String configFilePath = getPath(Common.configFileDir, file);
			configFilePath = SystemUtils.transformPathSeparator(configFilePath);

			if (filePathMap.containsKey(configFilePath)) {
				throwException(null, "配置文件不能重复," + file + " 重复出现");
			}

			Document document = getDocument(configFilePath);
			filePathMap.put(configFilePath, document);

			parse(document);
		}
	}

	public void print() {
		System.out.println("------------include标签 start--------------");
		for (Entry<String, Document> fileEntry : filePathMap.entrySet()) {
			String key = fileEntry.getKey();
			System.out.println("file:" + key);
		}
		System.out.println("------------include标签 end--------------");
	}

	public HashMap<String, Document> getFilePathMap() {
		return filePathMap;
	}

	public static void main(String[] args) {
		classTest();
	}

	private static void classTest() {
		IncludeParse parse = new IncludeParse();
		parse.startParse(Common.configFilePath);

		parse.print();
	}

}
