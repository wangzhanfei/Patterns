package com.wzf.test;

import com.wzf.constrant.State;
import com.wzf.constrant.State;

public class Test {

	public Test() {
		
		State state=State.NO_APPLICATION_CONTROLLER;
		
		String str=state.getStateDesc();
		System.out.println(str);
	}

	
	public static void main(String[] args) {
		new Test();
	}
}
