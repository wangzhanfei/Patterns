package com.wzf.test;

public interface LogInterface {

	/**
	 * 调试
	 */
	public void debug(String msg);

	/**
	 * 消息
	 */
	public void info(String msg);

	/**
	 * 警告
	 */
	public void warn(String msg);

	/**
	 * 错误
	 */
	public void error(String msg);

	/**
	 * 致命错误
	 */
	public void fatal(String msg);
}
