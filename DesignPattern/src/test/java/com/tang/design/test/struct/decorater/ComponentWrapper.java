package com.tang.design.test.struct.decorater;

/**
 * 
 * <p>2019年12月5日</p>
 * @author tangxing
 * <p> 包装对象 </p>
 */
public class ComponentWrapper extends Decorator {

	public ComponentWrapper(Component component) {
		super(component);
	}

	@Override
	public void operator() {
		System.out.println("方法调用之前");
		super.operator();
		System.out.println("方法调用之后");
	}

	

}
