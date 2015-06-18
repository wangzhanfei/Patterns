package com.wzf.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.wzf.datatype.ActionInfo;
import com.wzf.filter.ActionFilter;

public abstract class ApplicationController {

	protected ActionInfo actionInfo = null;

	/**
	 * 统一处理响应
	 * @param requestContext
	 * @param responseContext
	 */
	public abstract void handleReponse(RequestContext requestContext,
			ResponseContext responseContext);

	/**
	 * 处理请求，分配action
	 * @param requestContext
	 * @return
	 */
	public abstract ResponseContext handleRequest(RequestContext requestContext);

	public void setActionInfo(ActionInfo actionInfo) {
		this.actionInfo = actionInfo;
	}

	public List<ActionFilter> getActionFilterList() {
		List<ActionFilter> actionFilters = new ArrayList<ActionFilter>();
		if (actionInfo == null) {
			return actionFilters;
		}
		List<Class<?>> clsList = actionInfo.getClsList();
		for (Class<?> class1 : clsList) {
			System.out.println(class1.getName());
		}
		if (clsList == null || clsList.size() == 0) {
			return actionFilters;
		}
		for (Class<?> actionCls : clsList) {
			try {
				ActionFilter actionFilter = (ActionFilter) actionCls
						.newInstance();
				actionFilters.add(actionFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return actionFilters;
	}

	protected Object invoke() {
		Object object = null;
		try {
			object = actionInfo.getMethod().invoke(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

}
