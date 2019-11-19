package com.tang.design.test.create.factory.abs;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 北京农场 </p>
 */
public class BeijingFarmFactory extends FarmFactory{

	public BeijingFarmFactory() {
		super();
	}

	@Override
	protected void newAnimal() {
		animal = new House();
	}

	@Override
	protected void newPlant() {
		plant = new Apple();
	}

}
