package com.tang.design.test.create.builder;

/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 抽象的建造者 </p>
 */
public abstract class Decorator {

	protected Parlour parlour = new Parlour();
	
	/**
	 * 墙体
	 */
	protected abstract void buildWall();
	
	/**
	 * 电视
	 */
	protected abstract void buildTV();
	
	/**
	 * 沙发
	 */
	protected abstract void buildSofa();
	
	/**
	  *  获取最终客厅
	 * @return
	 */
	public Parlour getParlour() {
		return this.parlour;
	}
	
}
