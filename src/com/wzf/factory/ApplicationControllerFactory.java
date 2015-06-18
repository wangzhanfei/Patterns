package com.wzf.factory;

import java.util.HashMap;

import com.wzf.constrant.Common;
import com.wzf.constrant.State;
import com.wzf.context.ConfigContext;
import com.wzf.datatype.ActionInfo;
import com.wzf.datatype.ControllerInfo;
import com.wzf.interfaces.ApplicationController;
import com.wzf.interfaces.RequestContext;

public class ApplicationControllerFactory {

	private static ApplicationControllerFactory factory = null;

	public static ApplicationControllerFactory getInstance() {

		if (factory == null) {
			factory = new ApplicationControllerFactory();
		}
		return factory;
	}

	/**
	 * 创建控制器
	 * @param requestContext
	 * @return
	 */
	public ApplicationController createApplicationController(
			RequestContext requestContext) {

		String platformName = (String) requestContext
				.getAttribute(Common.PLATFORM);
		if (platformName == null) {
			requestContext.setStatues(State.NO_PLATFOMM_CONTROLLER_PARAMS);
			return null;
		}

		HashMap<String, ControllerInfo> controllerMap = ConfigContext
				.getPlatformControllerInfo(platformName);
		if (controllerMap == null) {
			requestContext.setStatues(State.NO_PLATFOMM_CONTROLLER_INFO);
			return null;
		}

		String controllerName = (String) requestContext
				.getAttribute(Common.APPLICATION_CONTROLLER_NAME);
		if (controllerName == null) {
			requestContext.setStatues(State.NO_APPLICATION_CONTROLLER_PARAMS);
			return null;
		}

		ControllerInfo controllerInfo = controllerMap.get(controllerName);
		if (controllerInfo == null) {
			requestContext.setStatues(State.NO_APPLICATION_CONTROLLER);
			return null;
		}

		Class<?> cls = controllerInfo.getCls();
		ApplicationController applicationController = null;
		try {
			applicationController = (ApplicationController) cls.newInstance();

			String actionName = (String) requestContext
					.getAttribute(Common.APPLICATION_ACTION_NAME);
			if (actionName == null) {
				requestContext.setStatues(State.NO_CONTROLLER_ACTION_PARAMS);
				return null;
			}

			ActionInfo actionInfo = controllerInfo.getActionMapper().get(
					actionName);
			if (actionInfo == null) {
				requestContext.setStatues(State.NO_CONTROLLER_ACTION);
				return null;
			}
			
			applicationController.setActionInfo(actionInfo);
			requestContext
					.setStatues(State.CREATE_APPLICATION_CONTROLLER_SUCCESS);
		} catch (Exception e) {
			requestContext.setStatues(State.CREATE_APPLICATION_CONTROLLER_FAIL);
			e.printStackTrace();
		}

		return applicationController;
	}
}
