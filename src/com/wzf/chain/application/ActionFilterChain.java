package com.wzf.chain.application;

import java.util.ArrayList;
import java.util.List;

import com.wzf.filter.ActionFilter;
import com.wzf.interfaces.ApplicationController;
import com.wzf.interfaces.RequestContext;
import com.wzf.interfaces.ResponseContext;

/**
 * @author wzf
 * 为应用控制器的action添加前置处理和后置处理
 */
public class ActionFilterChain {

	List<ActionFilter> actionFilterList = new ArrayList<ActionFilter>();

	private int currentFilterIndex = 0;

	private ResponseContext responseContext = null;

	private int maxIndex = 0;

	private ApplicationController controller;

	/**
	 * 
	 * @param controller 应用控制器
	 */
	public ActionFilterChain(ApplicationController controller) {
		this.controller = controller;
	}

	public void addActionFilter(ActionFilter filter) {
		actionFilterList.add(filter);
		setMaxIndex();
	}

	public void addAll(List<ActionFilter> filters) {
		actionFilterList = filters;
		setMaxIndex();
	}

	private void setMaxIndex() {
		maxIndex = actionFilterList.size() - 1;
	}

	/**
	 * 递归调用，根据请求参数过滤action操作
	 * @param requestContext
	 * @return
	 */
	public ResponseContext doFilter(RequestContext requestContext) {

		if (actionFilterList.size() == 0 || currentFilterIndex > maxIndex) {
			responseContext = controller.handleRequest(requestContext);
			return responseContext;
		}
		
		ActionFilter actionFilter = nextActionFilter();
		actionFilter.invoke(requestContext, this);

		return responseContext;
	}
	
	private ActionFilter nextActionFilter() {
		ActionFilter actionFilter = actionFilterList.get(currentFilterIndex);
		currentFilterIndex++;
		return actionFilter;
	}

}
