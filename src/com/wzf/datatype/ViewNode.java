package com.wzf.datatype;

import java.util.HashMap;

/**
 * @author Administrator
 * 树节点,树结构
 */
public class ViewNode {

	private HashMap<String, String> leafNodeMap = new HashMap<String, String>();

	private HashMap<String, ViewNode> childNodeMap = new HashMap<String, ViewNode>();

	private String nodeName="";
	
	private boolean isLeafEmpty = false;

	private boolean isChildEmpty = false;

	/**
	 * 添加叶子节点
	 * @param nodeKey
	 * @param value
	 */
	public void addLeafNode(String nodeKey, String value) {
		leafNodeMap.put(nodeKey, value);
		setLeafEmpty();
	}

	/**
	 * 添加子节点，但不是叶子节点
	 * @param nodeKey
	 * @param childNode
	 * @return
	 */
	public void addChildNode(String nodeKey, ViewNode childNode) {
		childNodeMap.put(nodeKey, childNode);
		setChildEmpty();
	}

	public HashMap<String, String> getLeafNodeMap() {
		return leafNodeMap;
	}

	public HashMap<String, ViewNode> getChildNodeMap() {
		return childNodeMap;
	}

	public boolean isLeafEmpty() {
		return isLeafEmpty;
	}

	public boolean isChildEmpty() {
		return isChildEmpty;
	}

	private void setLeafEmpty() {

		if (leafNodeMap.size() == 0) {
			this.isLeafEmpty = true;
		} else {
			this.isLeafEmpty = false;
		}
	}

	public void setChildEmpty() {

		if (childNodeMap.size() == 0) {
			this.isChildEmpty = true;
		} else {
			this.isChildEmpty = false;
		}
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
}
