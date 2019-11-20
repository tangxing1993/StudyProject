package com.tang.design.test.struct.proxy;

import java.lang.reflect.Method;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import junit.framework.TestCase;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 使用Cglib实现动态代理,可执行接口代理和无接口代理 ,有接口代理比无接口代理效率高 </p>
 */
public class CglibDynamicProxyTest extends TestCase{
	
	/**
	  * 测试接口代理
	 */
	@Test
	public void testInterfaceProxy() {
		Subject subjectProxy = (Subject)Enhancer.create(SubjectImpl.class, new Class[] {Subject.class}, new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				// obj代理后的子类
				// method 被代理的方法
				// args 方法参数
				// proxy 代理方法执行器
				System.out.println("--- Cglib代理执行之前 ---");
				// 调用增强之后的方法
				Object invoke = proxy.invokeSuper(obj, args);
				System.out.println("--- Cglib代理执行之后 ---");
				return invoke;
			}
		});
		subjectProxy.say();
	}
	
	/**
	 * 测试无接口代理
	 */
	@Test
	public void testNoInterfaceProxy() {
		Subject subjectProxy  = (Subject)Enhancer.create(SubjectImpl.class, new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				System.out.println("--- Cglib代理执行之前 ---");
				// 调用增强之后的方法
				Object invoke = proxy.invokeSuper(obj, args);
				System.out.println("--- Cglib代理执行之后 ---");
				return invoke;
			}
		});
		subjectProxy.say();
	}
	
}
