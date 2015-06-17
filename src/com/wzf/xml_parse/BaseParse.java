package com.wzf.xml_parse;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wzf.ThrowException;
import com.wzf.test.Log;

public abstract class BaseParse {

	public abstract void startParse();

	private String filePath = "E:\\MyEclipse\\Patterns\\config\\filter-config.xml";

	Log log = Log.getInstance();

	@SuppressWarnings("unchecked")
	protected List<Element> getElementList(Element node, String childNode) {
		return node.elements(childNode);
	}

	protected Element getElement(Element element, String nodeName) {
		return element.element(nodeName);
	}

	protected String getAttribute(Element element, String attrName) {
		return element.attributeValue(attrName);
	}

	protected void throwException(Object obj, String msg) {
		if (obj == null) {
			log.fatal(msg);
			throw new ThrowException(msg);
		}
	}

	protected Document getDocument() throws DocumentException {
		System.out.println(filePath);
		File file = new File(filePath);
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		return document;
	}

}
