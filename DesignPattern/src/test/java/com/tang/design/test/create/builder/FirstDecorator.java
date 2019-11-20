package com.tang.design.test.create.builder;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 装修工人1 </p>
 */
public class FirstDecorator extends Decorator {

	@Override
	protected void buildWall() {
		this.parlour.setWall("FirstWall");
	}

	@Override
	protected void buildTV() {
		this.parlour.setTV("FirstTV");
	}

	@Override
	protected void buildSofa() {
		this.parlour.setSofa("FirstSofa");
	}

}
