package com.tang.design.test.behavior.strategy;
/**
 * 
 * <p>2019年12月31日</p>
 * @author tangxing
 * <p></p>
 */
public class Context {

	private TypeStrategy typeStrategy;

	public TypeStrategy getTypeStrategy() {
		return typeStrategy;
	}

	public void setTypeStrategy(TypeStrategy typeStrategy) {
		this.typeStrategy = typeStrategy;
	}
	
	public void doSomeing() {
		typeStrategy.doSomeing();
	}
	
	
}
