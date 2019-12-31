package com.tang.design.test.behavior.template;
/**
 * 
 * <p>2019年12月31日</p>
 * @author tangxing
 * <p> 模版方法调用 </p>
 */
public class TemplateMethodPattern {

	public static void main(String[] args) {
		AbstractClass ac = new ConcreteClass();
		ac.templateMethod();
	}
	
}
