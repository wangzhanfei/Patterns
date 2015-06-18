package com.wzf.filter;

import com.wzf.interfaces.RequestContext;
import com.wzf.interfaces.ResponseContext;

public class TestActionFilter extends ActionFilter {

	private String name = "";
	
	public TestActionFilter() {
	}

	public TestActionFilter(String name) {
		this.name = name;
	}

	@Override
	public void doPreProcessing(RequestContext requestContext) {
		System.out.println("pre----" + name);
	}

	@Override
	public void doPostProcessing(RequestContext requestContext,
			ResponseContext responseContext) {
		System.out.println("post----" + name);
	}

}
