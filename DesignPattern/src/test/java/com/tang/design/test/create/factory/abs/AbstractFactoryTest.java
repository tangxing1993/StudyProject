package com.tang.design.test.create.factory.abs;


import org.junit.Test;

import junit.framework.TestCase;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 抽象工厂测试 </p>
 */
public class AbstractFactoryTest extends TestCase {

	@Test
	public void testBeijingFarmFactory() {
		FarmFactory f = new BeijingFarmFactory();
		f.print();
	}
	
}
