package com.tang.design.test.struct.bridge;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * 
 * <p>2019年12月3日</p>
 * @author tangxing
 * <p> 
 * 	桥接应用范围 :
 * 	 		当某个实体拥有多个变化的属性时,避免造成继承出现 n*n个子类,采用桥接的方式进行设计实体。
 * 	使用技巧 : 
 *      	使用抽象层聚合变化属性,将多个变化的属性拆解为借口并实现多种变化
 * </p>
 */
public class BridgeTest extends TestCase{

	@Test
	public void testHandBag() {
		// 获取黄色的挎包
		HandBag handBag = new HandBag();
		handBag.setColor(new YellowColor());
		System.out.println(handBag.getName());
		// 获取红色的挎包
		handBag.setColor(new RedColor());
		System.out.println(handBag.getName());
	}
	
	@Test
	public void testWallet() {
		Wallet wallet = new Wallet();
		wallet.setColor(new YellowColor());
		System.out.println(wallet);
	}
	
	
}
