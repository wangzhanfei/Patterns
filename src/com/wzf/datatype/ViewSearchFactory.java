package com.wzf.datatype;

import com.wzf.constrant.Common;
import com.wzf.constrant.Common.E_SearchType;
import com.wzf.context.RequestWrapper;

public class ViewSearchFactory {

	private static ViewSearchFactory searchFactory = null;

	public ViewSearchFactory() {

	}

	public static ViewSearchFactory getInstance() {
		if (searchFactory == null) {
			searchFactory = new ViewSearchFactory();
		}
		return searchFactory;
	}

	public ViewTree getViewTree(RequestWrapper wrapper) {

		ViewTree viewTree = null;
		E_SearchType searchType = (E_SearchType) wrapper
				.getAttribute(Common.SEARCH_TYPE);
		switch (searchType) {
		case Breadth_First_Search:
			viewTree = new BreadthFirstSearch();
			break;
		case Depth_First_Search:
			viewTree = new DepthFirstSearch();
			break;
		default:
			viewTree = new IndiscriminateSearch();
			break;
		}
		return viewTree;
	}
}
