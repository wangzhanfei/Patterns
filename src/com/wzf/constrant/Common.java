package com.wzf.constrant;

import java.io.File;

public class Common {

	public final static String filePath = "E:\\MyEclipse\\Workspaces\\Patterns\\config\\filter-config.xml";

	public final static String PLATFORM = "platform";

	public final static String APPLICATION_CONTROLLER_NAME = "application_controller_name";

	public final static String APPLICATION_ACTION_NAME = "application_action_name";

	public final static String rootPath;

	public final static String configFileDir;

	public final static String configFilePath;

	// 在这个域下搜寻视图，ViewSearchFactory将使用此常量,关联视图配置文件中view-list的name
	public final static String VIEW_DOMAIN = "view_domain";

	/**
	 * 搜索类型
	 */
	public final static String SEARCH_TYPE = "search_type";
	
	//搜索类型
	public static enum E_SearchType {
		Breadth_First_Search, Depth_First_Search,Indiscriminate_Search
	};

	static {
		rootPath = System.getProperty("user.dir");
		configFileDir = rootPath + File.separator + "config";
		configFilePath = configFileDir + File.separator + "config.xml";

		// System.out.println(rootPath);
		// System.out.println(configFileDir);
		// System.out.println(configFilePath);
	}
}
