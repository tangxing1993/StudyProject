package com.tang.design.test.create.factory.abs;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 牛对象 </p>
 */
public class Cattle implements Animal {

	@Override
	public void show() {
		System.out.println("--- 我是牛 ---");
	}

}
