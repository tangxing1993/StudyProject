package com.tang.design.test.behavior.strategy;
/**
 * 
 * <p>2019年12月31日</p>
 * @author tangxing
 * <p> 策略案例调用 </p>
 */
public class StrategyPattern {

	public static void main(String[] args) {
		TypeStrategy typeStrategy = new IntegerStrategy();
		Context context = new Context();
		context.setTypeStrategy(typeStrategy);
		context.doSomeing();
		typeStrategy = new StringStrategy();
		context.setTypeStrategy(typeStrategy);
		context.doSomeing();
	}
	
}
