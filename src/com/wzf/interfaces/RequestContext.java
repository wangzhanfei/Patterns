package com.wzf.interfaces;

import java.util.List;

import com.wzf.constrant.State;

public interface RequestContext {

	public void setAttribute(String name, Object object);

	public Object getAttribute(String name);

	public void validate();

	public String getCommandName();

	/**
	 * 获取请求状态
	 * @return
	 */
	public List<State> getStatusList();
	
	public State getStatus();
	
	/**
	 * 设置请求状态
	 * @param stateNum
	 */
	public void setStatues(State stateNum);
}
