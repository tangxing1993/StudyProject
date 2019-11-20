package com.tang.design.test.create.builder;

import org.junit.Test;

import junit.framework.TestCase;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 测试构建者模式 </p>
 */
public class BuilderTest extends TestCase{

	@Test
	public void testBuilderParlour() {
		Parlour parlour = new ProjectManager(new SecondDecorator()).getParlour();
		parlour.show();
	}
	
}
