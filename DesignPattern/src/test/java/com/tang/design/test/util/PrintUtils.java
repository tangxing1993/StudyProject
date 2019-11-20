package com.tang.design.test.util;

import java.util.Locale;

/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 打印工具接口 </p>
 */
public abstract class PrintUtils {

	/**
	 * 打印
	 * @param message
	 */
	public static void print(String message) {
		System.out.println(String.format(Locale.ROOT, "--- %s ---", message));
	}
	
}
