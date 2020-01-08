package com.tang.design.test.behavior.chain;
/**
 * 
 * <p>2020年1月8日</p>
 * @author tangxing
 * <p> 客户类 </p>
 */
public class ChainOfResponsibilityPattern {

	public static void main(String[] args) {
		
		ConcreteHandler handler1 = new ConcreteHandler();
		ConcreteHandler2 handler2 = new ConcreteHandler2();
		
		handler1.setNext(handler2);
		handler1.handlerRequest("two");
		
		
	}
	
	
}
