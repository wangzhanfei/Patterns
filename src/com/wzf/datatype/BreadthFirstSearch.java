package com.wzf.datatype;

/**
 * @author Administrator
 * 广度优先搜索
 */
public class BreadthFirstSearch extends ViewTree {

	/* (non-Javadoc)
	 * @see com.wzf.datatype.ViewTree#search(com.wzf.datatype.ViewNode, java.lang.String[])
	 * 
	 * @params 传递2个参数 ,1:节点名字 2:要搜索的映射名字
	 */
	@Override
	public String search(ViewNode nodeInfo, String... params) {

		String nodeName = params[0];
		String target = params[1];

		String result = null;
		E_NodeState nodeState = isEmpty(nodeInfo);

		// 叶子节点有效
		boolean leafNodeValid = (nodeInfo.getNodeName().equals(nodeName))
				&& (nodeState != E_NodeState.leaf_empty);

		if (leafNodeValid) {
			result = searchLeafNode(nodeInfo.getLeafNodeMap(), target);
			if (result != null) {
				return result;
			}
		}

		if (nodeState != E_NodeState.child_empty) {
			result = searchChildNode(nodeInfo.getChildNodeMap(), params);
			if (result != null) {
				return result;
			}
		}

		return null;
	}

}
