package com.tang.design.test.create.factory.abs;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 上海农场 </p>
 */
public class ShanghaiFarmFactory extends FarmFactory {

	@Override
	protected void newAnimal() {
		animal = new Cattle();
	}

	@Override
	protected void newPlant() {
		plant = new Banana();
	}

}
