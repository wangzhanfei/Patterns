package com.wzf.interfaces;


public interface ApplicationController {
	
	
	/**
	 * 统一处理响应
	 * @param requestContext
	 * @param responseContext
	 */
	public void handleReponse(RequestContext requestContext,
			ResponseContext responseContext);

	/**
	 * 处理请求，分配action
	 * @param requestContext
	 * @return
	 */
	public ResponseContext handleRequest(RequestContext requestContext);
}
