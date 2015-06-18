package com.wzf.utility.system;

import java.io.File;

import com.wzf.error.exceptions.ThrowException;

public class SystemUtils {

	public static enum E_System_Platform {
		windows, linux
	};

	private static E_System_Platform system_Platform = E_System_Platform.windows;

	public static E_System_Platform getCurrentSystemPlatform() {
		return system_Platform;
	}

	public static void setCurrentSystemPlatform(E_System_Platform platform) {
		system_Platform = platform;
	}

	public static String transformPathSeparator(final String path) {
		String _path = path;
		
		char c='/';
		switch (getCurrentSystemPlatform()) {
		case windows:
			c='\\';
			break;
		case linux:
			c='/';
			break;
		default:
			new ThrowException("不支持改系统平台");
			break;
		}
		_path=_path.replace(c, File.separatorChar);
		return _path;
	}
}



















