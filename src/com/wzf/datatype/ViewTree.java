package com.wzf.datatype;

import java.util.HashMap;
import java.util.Map.Entry;

public abstract class ViewTree {

	/**
	 * @author Administrator
	 * node_empty：整个节点是空的
	 * leaf_empty：树节点空
	 * child_empty:没有子节点
	 */
	enum E_NodeState {
		node_empty, leaf_empty, child_empty
	};

	
	/**
	 * 搜索
	 * @param nodeInfo
	 * @param params
	 * @return
	 */
	public abstract String search(ViewNode nodeInfo,String... params);

	protected String searchLeafNode(HashMap<String, String> leafNodeMap,
			String target) {

		String result = null;
		for (Entry<String, String> leafEntry : leafNodeMap.entrySet()) {
			String name = leafEntry.getKey();
			String value = leafEntry.getValue();
			if (target.equals(name)) {
				result = value;
				break;
			}
		}

		return result;
	}
	
	/**
	 * @param childNodeMap
	 * @param nodeName 如果nodeName 是null，只要有匹配就返回，非null是需要匹配NodeName否者不搜索
	 * @param target
	 * @return
	 */
	protected String searchChildNode(HashMap<String, ViewNode> childNodeMap,String... params) {

		String result = null;
		for (Entry<String, ViewNode> nodeEntry : childNodeMap.entrySet()) {
			ViewNode viewNode = nodeEntry.getValue();

			result = search(viewNode,params);

			if (result != null) {
				break;
			}
		}

		return result;
	}

	/**
	 * 当前子节点是否是空
	 * @return
	 */
	public E_NodeState isEmpty(ViewNode viewNode) {

		E_NodeState nodeState = E_NodeState.node_empty;

		if (viewNode.isLeafEmpty() && viewNode.isChildEmpty()) {
			nodeState = E_NodeState.node_empty;
			return nodeState;
		}

		if (viewNode.isLeafEmpty()) {
			nodeState = E_NodeState.leaf_empty;
		}

		if (viewNode.isChildEmpty()) {
			nodeState = E_NodeState.child_empty;
		}

		return nodeState;
	}
}
