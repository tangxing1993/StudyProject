package com.tang.design.test.struct.decorater;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 
 * <p>2019年12月5日</p>
 * @author tangxing
 * <p> 包装类测试 </p>
 */
public class ComponentWrapperTest extends TestCase{

	@Test
	public void test() {
		ComponentWrapper componentWrapper = new ComponentWrapper(new ComponentImpl());
		componentWrapper.operator();
	}
	
	
}
