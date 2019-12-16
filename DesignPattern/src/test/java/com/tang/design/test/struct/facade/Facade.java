package com.tang.design.test.struct.facade;
/**
 * 
 * <p>2019年12月5日</p>
 * @author tangxing
 * <p> 外观实体 </p>
 */
public class Facade {

	
	private SubSystem01 sub1 = new SubSystem01();
	
	private SubSystem02 sub2 = new SubSystem02();
	
	private SubSystem03 sub3 = new SubSystem03();
	
	public void method() {
		sub1.operator();
		sub2.toSay();
		sub3.hello();
	}
	
	
}
