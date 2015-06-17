package com.wzf.xml_parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wzf.ThrowException;
import com.wzf.test.Log;

public abstract class BaseParse {

	private String filePath = "E:\\MyEclipse\\Patterns\\config\\filter-config.xml";

	protected Log log = Log.getInstance();

	protected Document document;

	public abstract void startParse();

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

	protected Document getDocument() throws DocumentException,
			FileNotFoundException {

		if (document != null) {
			return document;
		}
		File file = new File(filePath);

		// try {
		FileInputStream fis = new FileInputStream(file);

		// ByteArrayOutputStream bos = new ByteArrayOutputStream();
		// int len = -1;
		// byte buf[] = new byte[1024 * 8];
		// while ((len = fis.read(buf)) != -1) {
		// bos.write(buf, 0, len);
		// }
		// bos.flush();
		// bos.close();
		// System.out.println(bos.toString());
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		SAXReader reader = new SAXReader();
		Document document = reader.read(fis);
		return document;
	}

	protected Document getDocument(String filePath) throws DocumentException,
			FileNotFoundException {
		System.out.println(filePath);
		File file = new File(filePath);

		FileInputStream fis = new FileInputStream(file);

		SAXReader reader = new SAXReader();
		Document document = reader.read(fis);
		return document;
	}

}
