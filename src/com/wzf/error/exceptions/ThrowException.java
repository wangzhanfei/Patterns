package com.wzf.error.exceptions;

public class ThrowException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ThrowException() {
		super("自定义异常");
	}

	public ThrowException(String message) {
		super(message);
	}
}
