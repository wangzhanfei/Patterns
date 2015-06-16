package com.wzf.interfaces;

public interface RequestContext {
	
	public void setAttribute(String name, Object object);

	public Object getAttribute(String name);

	public void validate();

	public String getCommandName();
}
