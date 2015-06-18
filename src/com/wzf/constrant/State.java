package com.wzf.constrant;

public interface State {
	
	/**
	 * 通用错误
	 */
	public final int COMMON_ERROR=-1;
	
	/**
	 * 没有平台控制器信息
	 */
	public final int NO_PLATFOMM_CONTROLLER_INFO=101;
	
	/**
	 * 没有平台控制器参数
	 */
	public final int NO_PLATFOMM_CONTROLLER_PARAMS=102;
	
	/**
	 * 应用控制器请求参数不存在
	 */
	public final int NO_APPLICATION_CONTROLLER_PARAMS=103;
	
	/**
	 * 没有应用控制器
	 */
	public final int NO_APPLICATION_CONTROLLER=104;
	
	
	/**
	 * 创建应用控制器失败
	 */
	public final int CREATE_APPLICATION_CONTROLLER_FAIL=105;
	
	/**
	 * 没有控制器action的参数
	 */
	public final int NO_CONTROLLER_ACTION_PARAMS=105;
	
	/**
	 * 没有控制器action方法
	 */
	public final int NO_CONTROLLER_ACTION=106;
	
	/**
	 * 创建应用控制器成功
	 */
	public final int CREATE_APPLICATION_CONTROLLER_SUCCESS=110;
}























