package com.tang.design.test.struct.bridge;
/**
 * 
 * <p>2019年12月3日</p>
 * @author tangxing
 * <p> 钱包实体 </p>
 */
public class Wallet extends Bag {

	@Override
	public String getName() {
		return this.getColor().getName() + ":" + this.getClass().getSimpleName();
	}

}
