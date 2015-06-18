package com.wzf.constrant;

/**
 * @author Administrator
 * 应用系统状态
 */
public enum State {

	/**
	 * 通用错误
	 */
	COMMON_ERROR(-1, "通用错误"),

	//------------------------------------
	//应用控制器相关状态 101-200范围   110为正常响应流程
	//------------------------------------
	/**
	 * 没有平台控制器信息
	 */
	NO_PLATFOMM_CONTROLLER_INFO(101, "没有平台控制器信息"),

	/**
	 * 没有平台控制器参数
	 */
	NO_PLATFOMM_CONTROLLER_PARAMS(102, "没有平台控制器参数"),

	/**
	 * 应用控制器请求参数不存在
	 */
	NO_APPLICATION_CONTROLLER_PARAMS(103, "应用控制器请求参数不存在"),

	/**
	 * 没有应用控制器
	 */
	NO_APPLICATION_CONTROLLER(104, "没有应用控制器"),

	/**
	 * 创建应用控制器失败
	 */
	CREATE_APPLICATION_CONTROLLER_FAIL(105, "创建应用控制器失败"),

	/**
	 * 没有控制器action的参数
	 */
	NO_CONTROLLER_ACTION_PARAMS(105, "没有控制器action的参数"),

	/**
	 * 没有控制器action方法
	 */
	NO_CONTROLLER_ACTION(106, "没有控制器action方法"),

	/**
	 * 创建应用控制器成功
	 */
	CREATE_APPLICATION_CONTROLLER_SUCCESS(110, "创建应用控制器成功");

	private int stateCode;

	private String stateDesc;

	private State(int stateCode, String stateDesc) {
		this.stateCode = stateCode;
		this.stateDesc = stateDesc;
	}

	public int getStateCode() {
		return this.stateCode;
	}

	public String getStateDesc() {
		return this.stateDesc;
	}
}
