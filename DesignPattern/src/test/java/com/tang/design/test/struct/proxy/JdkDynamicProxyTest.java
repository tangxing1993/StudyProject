package com.tang.design.test.struct.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import junit.framework.TestCase;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 通过JDK提供的sdk来实现动态代理: 被代理的类必须拥有接口 ( 有接口的代理速度快 ) </p>
 */
public class JdkDynamicProxyTest extends TestCase {
	
	@Test
	public void testJdkDynamicProxy() {
		// 要被代理的对象
		SubjectImpl subject = new SubjectImpl();
		Subject subjectProxy = (Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class<?>[] {Subject.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("--- JDK动态代理执行之前 ---");
				Object invoke = method.invoke(subject, args);
				System.out.println("--- JDK动态代理执行之后 ---");
				return invoke;
			}
		});
		subjectProxy.say();
	}
	
}
