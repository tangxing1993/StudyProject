package com.tang.design.test.create.factory.abs;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 苹果对象 </p>
 */
public class Apple implements Plant {

	@Override
	public void show() {
		System.out.println("--- 我是苹果 ---");
	}

}
