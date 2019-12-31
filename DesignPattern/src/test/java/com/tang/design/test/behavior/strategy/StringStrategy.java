package com.tang.design.test.behavior.strategy;

public class StringStrategy implements TypeStrategy {

	@Override
	public void doSomeing() {
		System.out.println("处理字符串操作...");
	}

}
