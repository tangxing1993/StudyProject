package com.tang.design.test.struct.proxy;


import org.junit.Test;

import junit.framework.TestCase;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 静态代理测试 </p>
 */
public class StaticProxyTest extends TestCase{
	
	/**
	 * 静态代理增加了类实现  通过组合的模式实现了代理
	 */
	@Test
	public void testStaticProxy() {
		new SubjectStaticProxy(new SubjectImpl()).say();
	}
	
}
