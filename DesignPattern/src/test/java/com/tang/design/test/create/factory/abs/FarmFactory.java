package com.tang.design.test.create.factory.abs;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 抽象农场工厂 </p>
 */
public abstract class FarmFactory {
	protected Animal animal;
	protected Plant plant;
	
	public FarmFactory() {
		super();
		newAnimal();
		newPlant();
	}
	// 创建动物
	protected abstract void newAnimal();
	// 创建植物
	protected abstract void newPlant();
	
	// 打印
	public void print() {
		animal.show();
		plant.show();
	}
	
}
