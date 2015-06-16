package com.wzf.chain;

import com.wzf.interfaces.ApplicationController;
import com.wzf.interfaces.RequestContext;
import com.wzf.interfaces.ResponseContext;

public class BaseApplicationController implements ApplicationController{

	@Override
	public void handleReponse(RequestContext requestContext,
			ResponseContext responseContext) {
		
	}

	@Override
	public ResponseContext handleRequest(RequestContext requestContext) {
		
		
		
		return null;
	}

}
