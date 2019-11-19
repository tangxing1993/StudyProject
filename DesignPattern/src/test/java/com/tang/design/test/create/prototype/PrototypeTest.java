package com.tang.design.test.create.prototype;

import junit.framework.TestCase;

/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 原型对象测试 </p>
 */
public class PrototypeTest extends TestCase{
	
	public static void main(String[] args) throws CloneNotSupportedException {
		Prototype instance = new Prototype();
		instance.setName("原型对象");
		System.out.println("原型:" + instance.toString());
		Prototype instanceClone = (Prototype) instance.clone();
		System.out.println("克隆:" + instanceClone.toString());
		System.out.println("原型和克隆对象是否相等:" + instance.equals(instanceClone));
	}
	
}

/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 构建原型对象类 </p>
 */
class Prototype implements Cloneable{
	
	private String name;
	
	public Prototype() {
		System.out.println("--- 原型创建成功 ---");
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Prototype [name=" + name + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		System.out.println("--- 原型复制成功 ---");
		return super.clone();
	}
	
	
}
