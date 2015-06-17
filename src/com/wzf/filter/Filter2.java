package com.wzf.filter;

import com.wzf.interfaces.RequestContext;
import com.wzf.interfaces.ResponseContext;

public class Filter2 extends ActionFilter {

	public Filter2() {
		System.out.println("Filter2");
	}

	@Override
	public void doPreProcessing(RequestContext requestContext) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doPostProcessing(RequestContext requestContext,
			ResponseContext responseContext) {
		// TODO Auto-generated method stub

	}

}
