package com.wzf.interfaces;

import java.util.List;

public interface RequestContext {

	public void setAttribute(String name, Object object);

	public Object getAttribute(String name);

	public void validate();

	public String getCommandName();

	/**
	 * 获取请求状态
	 * @return
	 */
	public List<Integer> getStatusList();
	
	public int getStatus();
	
	/**
	 * 设置请求状态
	 * @param stateNum
	 */
	public void setStatues(Integer stateNum);
}
