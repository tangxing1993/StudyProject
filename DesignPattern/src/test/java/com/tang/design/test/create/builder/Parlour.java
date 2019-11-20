package com.tang.design.test.create.builder;

import java.util.Locale;

import lombok.Data;

/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 客厅对象 </p>
 */
@Data
public class Parlour {

	private String wall; // 强
	
	private String TV;  // 电视
	
	private String sofa; // 沙发
	
	public void show() {
		System.out.println(String.format(Locale.ROOT, "Parlour: [ %s , %s , %s  ] ", wall, TV, sofa));
	}
	
}
