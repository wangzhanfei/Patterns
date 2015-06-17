package com.wzf.chain;

import java.util.ArrayList;
import java.util.List;

import com.wzf.filter.ActionFilter;
import com.wzf.filter.TestActionFilter;
import com.wzf.interfaces.ApplicationController;
import com.wzf.interfaces.RequestContext;
import com.wzf.interfaces.ResponseContext;

public class Test {

	public Test() {

		List<ActionFilter> actionFilterList = new ArrayList<ActionFilter>();

		char a = '-';
		for (int i = 0; i < 10; i++) {

			String str = "";
			for (int j = 0; j < i; j++) {
				str += a;
			}
			str += i;
			ActionFilter actionFilter = new TestActionFilter(str);

			actionFilterList.add(actionFilter);
		}

		ApplicationController controller = new ApplController();

		ActionFilterChain actionFilterChain = new ActionFilterChain(controller);
		actionFilterChain.addAll(actionFilterList);

		RequestContext requestContext = new RequestContext() {

			@Override
			public void setAttribute(String name, Object object) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public Object getAttribute(String name) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void validate() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getCommandName() {
				// TODO Auto-generated method stub
				return null;
			}
		};

		ResponseContext responseContext = actionFilterChain
				.doFilter(requestContext);

		// if (responseContext == null) {
		// System.out.println("requestContext is null");
		// } else {
		// System.out.println("requestContext is not null");
		// }
	}

	public static void main(String[] args) {
		new Test();
	}
}
