package com.wzf.test;

import com.wzf.chain.application.ActionFilterChain;
import com.wzf.constrant.Common;
import com.wzf.constrant.State;
import com.wzf.context.RequestWrapper;
import com.wzf.factory.ApplicationControllerFactory;
import com.wzf.interfaces.ApplicationController;
import com.wzf.interfaces.RequestContext;
import com.wzf.interfaces.ResponseContext;
import com.wzf.xml_parse.ConfigMediator;

/**
 * @author Administrator
 * 应用控制器请求调用测试
 */
public class ApplicationContollerTest {

	public ApplicationContollerTest() {

	}

	private void AppcationContollerAndActionFilter() {
		ConfigMediator configMediator = new ConfigMediator();
		configMediator.startParse(Common.configFilePath, true);

		RequestContext requestContext = new RequestWrapper();
		requestContext.setAttribute(Common.PLATFORM, "mobile");
		requestContext.setAttribute(Common.APPLICATION_CONTROLLER_NAME,
				"applController");
		requestContext.setAttribute(Common.APPLICATION_ACTION_NAME, "t_name1");

		ApplicationControllerFactory controllerFactory = ApplicationControllerFactory
				.getInstance();
		ApplicationController controller = controllerFactory
				.createApplicationController(requestContext);

		System.out.println(requestContext.getStatus());
		if (requestContext.getStatus() == State.CREATE_APPLICATION_CONTROLLER_SUCCESS) {
			ActionFilterChain actionFilterChain = new ActionFilterChain(
					controller);
			actionFilterChain.addAll(controller.getActionFilterList());

			ResponseContext responseContext = actionFilterChain
					.doFilter(requestContext);
		}
	}

	public static void main(String[] args) {
		ApplicationContollerTest contollerTest = new ApplicationContollerTest();
		contollerTest.AppcationContollerAndActionFilter();
	}

}
