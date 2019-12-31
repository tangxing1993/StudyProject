package com.tang.design.test.behavior.strategy;
/**
 * 
 * <p>2019年12月31日</p>
 * @author tangxing
 * <p> 处理整形数据 </p>
 */
public class IntegerStrategy implements TypeStrategy {

	@Override
	public void doSomeing() {
		System.out.println("我是整形数据...");
	}

}
