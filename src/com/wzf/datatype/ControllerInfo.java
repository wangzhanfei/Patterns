package com.wzf.datatype;

import java.util.HashMap;
import java.util.Map.Entry;

public final class ControllerInfo {

	private HashMap<String, ActionInfo> actionMapper = new HashMap<String, ActionInfo>();

	private Class<?> cls;

	public HashMap<String, ActionInfo> getActionMapper() {
		return actionMapper;
	}

	public void setActionMapper(HashMap<String, ActionInfo> actionMapper) {
		this.actionMapper = actionMapper;
	}

	public Class<?> getCls() {
		return cls;
	}

	public void setCls(Class<?> cls) {
		this.cls = cls;
	}

	@Override
	public String toString() {
		String string = "\n		------控制器action信息\n";
		for (Entry<String, ActionInfo> entry : actionMapper.entrySet()) {
			String name = entry.getKey();
			ActionInfo actionInfo = entry.getValue();

			string += "			------action名称：" + name + "     " + actionInfo.toString() + "\n";
		}
		return string;
	}

}
