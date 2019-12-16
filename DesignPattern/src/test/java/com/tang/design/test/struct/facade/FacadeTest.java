package com.tang.design.test.struct.facade;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 
 * <p>2019年12月5日</p>
 * @author tangxing
 * <p> 外观模式测试 </p>
 */
public class FacadeTest extends TestCase{
	
	/**
	 * 迪米特法则: 
	 *      只与自己有关系的进行交互
	 */
	@Test
	public void test() {
		Facade facade  = new Facade();
		facade.method();
		
	}
	
}
