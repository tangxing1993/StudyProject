package com.tang.design.test.create.factory.simple;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 简单工厂对象测试 </p>
 */
public class SimpleFactoryTest extends TestCase {

	/**
	 * 简单工厂模式:
	 * 优点:
	 *     1. 不必知道产品的生产细节,只需要了解产品的类型即可创建产品对象
	 *     2. 可以通过配置文件来生产不同的产品
	 * 缺点:
	 * 	   1. 增加系统中类的个数,引起理解复杂度
	 * 	   2. 扩展困难,增加新产品需要修改工厂类 【破坏了开闭原则】
	 */	
	
	/**
	 * 生产奔驰车
	 */
	@Test
	public void testBenz() {
		CarFactory.getCar(CarFactory.TYPE_BENZE).driver();
	}
	
	/**
	 * 生产奥迪
	 */
	@Test
	public void testAudi() {
		CarFactory.getCar(CarFactory.TYPE_AUDI).driver();
	}
	
}
