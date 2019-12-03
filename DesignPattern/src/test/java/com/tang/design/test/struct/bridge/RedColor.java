package com.tang.design.test.struct.bridge;
/**
 * 
 * <p>2019年12月3日</p>
 * @author tangxing
 * <p> 红色实体 </p>
 */
public class RedColor implements Color {

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

}
