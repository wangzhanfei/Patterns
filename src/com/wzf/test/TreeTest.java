package com.wzf.test;

import java.util.Date;

import com.wzf.datatype.BreadthFirstSearch;
import com.wzf.datatype.DepthFirstSearch;
import com.wzf.datatype.IndiscriminateSearch;
import com.wzf.datatype.ViewNode;
import com.wzf.datatype.ViewTree;

public class TreeTest {

	public TreeTest() {

		ViewNode rootNode = new ViewNode();
		rootNode.setNodeName("rootNode");
		rootNode.addLeafNode("1", "1");
		rootNode.addLeafNode("2", "2");
		rootNode.addLeafNode("3", "3");

		ViewNode node1 = new ViewNode();
		node1.setNodeName("node1");
		rootNode.addChildNode("4", node1);

		node1.addLeafNode("5", "5");
		node1.addLeafNode("6", "6");
		node1.addLeafNode("7", "7");

		ViewNode node2 = new ViewNode();
		node1.addChildNode("8", node2);
		node2.setNodeName("node2");

		node2.addLeafNode("9", "9");
		node2.addLeafNode("10", "10");
		node2.addLeafNode("11", "11");

		ViewNode node3 = new ViewNode();
		node3.setNodeName("node3");
		node2.addChildNode("12", node3);

		node3.addLeafNode("13", "13");
		node3.addLeafNode("14", "14");
		node3.addLeafNode("15", "15");
		
		
		String nodeName = "node3";
		String target = "15";

		IndiscriminateSearch(rootNode, target);
		DepthFirstSearch(rootNode, nodeName, target);
		BreadthFirstSearch(rootNode, nodeName, target);

	}

	private void DepthFirstSearch(ViewNode rootNode, String nodeName,
			String target) {

		DepthFirstSearch viewTree = new DepthFirstSearch();

		long start = getTime();
		String result = viewTree.search(rootNode, nodeName, target);
		long end = getTime();

		System.out.println("深度搜索耗时:" + (end - start));

		print(result);
	}

	private void BreadthFirstSearch(ViewNode rootNode, String nodeName,
			String target) {

		BreadthFirstSearch viewTree = new BreadthFirstSearch();

		long start = getTime();
		String result = viewTree.search(rootNode, nodeName, target);
		long end = getTime();

		System.out.println("广度搜索耗时:" + (end - start));

		print(result);
	}

	private void IndiscriminateSearch(ViewNode rootNode, String target) {

		IndiscriminateSearch viewTree = new IndiscriminateSearch();

		long start = getTime();
		String result = viewTree.search(rootNode, target);
		long end = getTime();

		System.out.println("无差别搜索耗时:" + (end - start));

		print(result);
	}

	private void print(String result) {
		if (result != null) {
			System.out.println("			result:" + result);
		} else {
			System.out.println("			result is null");
		}
	}

	private long getTime() {
		return new Date().getTime();
	}

	public static void main(String[] args) {
		new TreeTest();
	}
}
