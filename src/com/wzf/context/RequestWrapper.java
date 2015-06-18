package com.wzf.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wzf.constrant.Common;
import com.wzf.constrant.State;
import com.wzf.interfaces.RequestContext;

public class RequestWrapper implements RequestContext{

	HashMap<String, Object> mapper=new HashMap<String, Object>();
	
	private List<Integer> stateList=new ArrayList<Integer>();
	
	private int state=State.COMMON_ERROR;
	
	@Override
	public void setAttribute(String name, Object object) {
		mapper.put(name, object);
	}

	@Override
	public Object getAttribute(String name) {
		return mapper.get(name);
	}

	@Override
	public void validate() {
	
		
	}

	@Override
	public String getCommandName() {
	
		return null;
	}

	@Override
	public int getStatus() {
		return state;
	}

	@Override
	public void setStatues(Integer stateNum) {
		state=stateNum;
		stateList.add(stateNum);
	}

	@Override
	public List<Integer> getStatusList() {
		return stateList;
	}

}
