package com.tang.design.test.create.singleton;

import junit.framework.TestCase;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 测试饿汉式单例 </p>
 */
public class HangrySingletonTest extends TestCase {
	
	public static void main(String[] args) {
		HangrySingleton.getInstance().print();
	}
	
}
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 饿汉式单例对象 </p>
 */
class HangrySingleton implements IPrint{

	// 构造单例实例
	private static final HangrySingleton instance = new HangrySingleton();
	
	// 私有化构造方法
	private HangrySingleton() {}
	
	// 获取单例实例
	public static HangrySingleton getInstance() {
		return instance;
	}
	
	@Override
	public void print() {
		System.out.println("--- 我是饿汉式单例实例 ---");
	}
}