package com.tang.design.test.behavior.template;
/**
 * 
 * <p>2019年12月31日</p>
 * @author tangxing
 * <p> 定义一个抽象类 </p>
 */
public abstract class AbstractClass {
	
	// 模版方法
	public void templateMethod() {
		hookMethod1();
		if(hookMethod2()) {
			specificMethod();
		}
		abstractMethod1();
		abstractMethod2();
		
	}
	
	// 钩子方法2
	public boolean hookMethod2() {
		return true;
	}
	
	// 钩子方法1
	public void hookMethod1() {};

	// 抽象方法二
	protected abstract void abstractMethod2();
	// 抽象方法一
	protected abstract void abstractMethod1();

	// 具体方法
	public void specificMethod() {
		System.out.println("抽象类中具体的方法被调用...");
	};
	
	
	
	
}
