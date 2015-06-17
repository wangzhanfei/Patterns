package com.wzf.filter;

import com.wzf.chain.ActionFilterChain;
import com.wzf.interfaces.RequestContext;
import com.wzf.interfaces.ResponseContext;

public abstract class ActionFilter {

	/**
	 * action前置处理
	 */
	public abstract void doPreProcessing(RequestContext requestContext);

	/**
	 * action后置处理
	 */
	public abstract void doPostProcessing(RequestContext requestContext,ResponseContext responseContext);

	public void invoke(RequestContext requestContext,ActionFilterChain chain) {
		
		doPreProcessing(requestContext);

		ResponseContext responseContext=chain.doFilter(requestContext);

		doPostProcessing(requestContext, responseContext);
	}

}
