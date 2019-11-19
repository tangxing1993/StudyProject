package com.tang.design.test.create.singleton;

import junit.framework.TestCase;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 懒汉式单例模式 </p>
 */
public class LazySingletonTest extends TestCase{

	/**
	 * 测试单例程序
	 */
	public static void main(String[] args) {
		 Singleton.getInstance().print();
	}
	
}

/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 创建懒汉式单例类 </p>
 */
class Singleton implements IPrint{
	
	// volatile  保证每个线程获取到变量的最新值,避免脏读
	private static volatile Singleton instance;
	
	// 私有化构造方法
	private Singleton() {}
	
	// 获取单例实例
	public static synchronized Singleton getInstance() {
			if(instance == null) {
				instance = new Singleton();
			}
			return instance;
	}
	
	@Override
	public void print() {
		System.out.println("--- 我是懒汉式单例实例 ---");
	}

}