package com.wzf.datatype;

import junit.framework.Assert;

import com.wzf.constrant.Common;
import com.wzf.context.RequestWrapper;

public class ViewFactory {

	private static ViewNode viewNode;

	private static ViewFactory viewFactory = null;

	public ViewFactory() {
		Assert.assertNotNull("viewNode is null", viewNode);
	}

	public static ViewFactory getInstance() {
		if (viewFactory == null) {
			viewFactory = new ViewFactory();
		}
		return viewFactory;
	}

	public String getView(RequestWrapper wrapper) {
		String viewDomain = (String) wrapper.getAttribute(Common.VIEW_DOMAIN);
		return viewDomain;
	}

	public static void setViewNode(ViewNode viewNode) {
		ViewFactory.viewNode = viewNode;
	}

}
