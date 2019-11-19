package com.tang.design.test.create.factory.simple;
/**
 * 
 * <p>2019年11月19日</p>
 * @author tangxing
 * <p> 汽车工厂 </p>
 */
public abstract class CarFactory {

	// 奔驰类型
	public static final String TYPE_BENZE = "benz";
	// 奥迪类型
	public static final String TYPE_AUDI = "audi";
	
	/**
	 * 简单工厂创建对象实例
	 * @param carType {@link  CarFactory.TYPE_BENZE}
	 * @return
	 */
	public static Car getCar(String carType) {
		Car car = null;
		switch(carType) {
			case TYPE_BENZE:  car = new Benz();break;
			case TYPE_AUDI:  car = new Audi();break;
			default: car = new Benz();break;
		}
		return car;
	}
	
}
