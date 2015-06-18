package com.wzf.datatype;

/**
 * @author Administrator
 * 无差别搜索，优先搜索广度，然后深度,在搜索过程中只要找到匹配的值就立马返回
 */
public class IndiscriminateSearch extends ViewTree {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wzf.datatype.ViewTree#search(com.wzf.datatype.ViewNode,
	 * java.lang.String[])
	 * 
	 * @params 传递1个参数,要搜索的名字
	 */
	@Override
	public String search(ViewNode nodeInfo, String... params) {

		String target = params[0];

		String result = null;
		E_NodeState nodeState = isEmpty(nodeInfo);

		if (nodeState != E_NodeState.leaf_empty) {
			result = searchLeafNode(nodeInfo.getLeafNodeMap(), target);
			if (result != null) {
				return result;
			}
		}

		if (nodeState != E_NodeState.child_empty) {
			result = searchChildNode(nodeInfo.getChildNodeMap(), target);
			if (result != null) {
				return result;
			}
		}

		return null;
	}

}
