package com.tang.design.test.struct.decorater;
/**
 * 
 * <p>2019年12月5日</p>
 * @author tangxing
 * <p> 组件的实现 </p>
 */
public class ComponentImpl implements Component {

	@Override
	public void operator() {
		System.out.println(ComponentImpl.class.getSimpleName() + "实现");
	}

}
