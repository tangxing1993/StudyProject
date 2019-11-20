package com.tang.design.test.create.builder;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 装修工人二 </p>
 */
public class SecondDecorator extends Decorator {

	@Override
	protected void buildWall() {
		this.parlour.setWall("SecondWall");
	}

	@Override
	protected void buildTV() {
		this.parlour.setTV("SecondTV");
	}

	@Override
	protected void buildSofa() {
		this.parlour.setSofa("SecondSofa");
	}

}
