package com.tang.design.test.struct.proxy;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 静态代理主题 </p>
 */
public class SubjectStaticProxy implements Subject {
	
	private final Subject subject;
	
	public SubjectStaticProxy(Subject subject) {
		super();
		this.subject = subject;
	}

	@Override
	public void say() {
		this.before();
		this.subject.say();
		this.after();
	}
	
	private void before() {
		System.out.println("--- 主题执行之前 ---");
	}
	
	private void after() {
		System.out.println("--- 主题执行之后 ---");
	}

}
