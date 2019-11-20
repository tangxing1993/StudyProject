package com.tang.design.test.struct.proxy;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 主题接口的实现 </p>
 */
public class SubjectImpl implements Subject {

	@Override
	public void say() {
		System.out.println("--- 我是真实主题 ---");
	}

}
