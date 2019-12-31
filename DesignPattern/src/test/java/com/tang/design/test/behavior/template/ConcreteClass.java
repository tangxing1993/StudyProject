package com.tang.design.test.behavior.template;
/**
 * 
 * <p>2019年12月31日</p>
 * @author tangxing
 * <p> 具体实现类 </p>
 */
public class ConcreteClass extends AbstractClass {
	
	@Override
	public boolean hookMethod2() {
		return false;
	}

	@Override
	public void hookMethod1() {
		System.out.println("重写钩子方法一...");
	}

	@Override
	protected void abstractMethod2() {
		System.out.println("抽象方法一被调用....");
	}

	@Override
	protected void abstractMethod1() {
		System.out.println("抽象方法二被调用...");
	}

}
