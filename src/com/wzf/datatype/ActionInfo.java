package com.wzf.datatype;

import java.lang.reflect.Method;
import java.util.List;

public class ActionInfo {

	private List<String> filterRefList;

	private List<Class<?>> clsList;
	
	private String actionName;

	private Method method;

	public List<String> getFilterRefList() {
		return filterRefList;
	}

	public void setFilterRefList(List<String> filterRefList) {
		this.filterRefList = filterRefList;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public List<Class<?>> getClsList() {
		return clsList;
	}

	public void setClsList(List<Class<?>> clsList) {
		this.clsList = clsList;
	}

	@Override
	public String toString() {
//		Assert.assertNotNull("filterRefList is null", filterRefList);
		String string = "\n----------过滤器start\n";
		for (Class<?> str : clsList) {
			string += str.getName() + "    \n";
		}
		string+="----------过滤器end\n";
		string += "actionName=" + actionName + "    methodName="
				+ method.getName();
		return string;
	}

}
