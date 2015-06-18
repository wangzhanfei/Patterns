package com.wzf.xml_parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wzf.error.exceptions.ThrowException;
import com.wzf.test.Log;

public abstract class BaseParse {

	protected Log log = Log.getInstance();

	protected Document document;

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

	protected Document getDocument(String filePath) {
		File file = new File(filePath);

		Document document = null;
		try {
			FileInputStream fis = new FileInputStream(file);

			SAXReader reader = new SAXReader();
			document = reader.read(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull("document is null", document);
		return document;
	}

	protected abstract List<Element> parse(Document document);

	protected String getPath(String configFileDir, String filePath) {

		String file = "";
		if (filePath.startsWith("/")) {
			file = configFileDir + filePath;
		} else {
			file = configFileDir + File.separator + filePath;
		}
		
		//这里需要根据平台转换,如果是windows平台，需要使用\，linux平台需要/
		
		file = file.replace("/", File.separator);

		return file;
	}
	
	public abstract void print();

}
