package com.wzf.test;

public class Log implements LogInterface {

	private static Log log = null;

	public static Log getInstance() {

		if (log == null) {
			log = new Log();
		}
		return log;
	}

	@Override
	public void debug(String msg) {
		System.out.println(msg);

	}

	@Override
	public void info(String msg) {
		System.out.println(msg);

	}

	@Override
	public void warn(String msg) {
		System.out.println(msg);

	}

	@Override
	public void error(String msg) {
		System.out.println(msg);

	}

	@Override
	public void fatal(String msg) {
		System.out.println(msg);
	}

}
