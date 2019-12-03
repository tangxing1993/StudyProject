package com.tang.design.test.struct.bridge;

/**
 * 
 * <p>2019年12月3日</p>
 * @author tangxing
 * <p> 包的抽象实体 </p>
 */
public abstract class Bag {

	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public abstract String getName();
	
}
