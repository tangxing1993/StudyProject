package com.tang.design.test.behavior.chain;
/**
 * 
 * <p>2020年1月8日</p>
 * @author tangxing
 * <p> 具体处理器2 </p>
 */
public class ConcreteHandler2 extends Handler {

	@Override
	public void handlerRequest(String request) {
		if("two".equals(request)) {
			System.out.println("处理器2处理请求...");
		}else {
			
			if(getNext() != null ) {
				getNext().handlerRequest(request);
			}else {
				System.out.println("没有处理器处理请求...");
			}
			
		}
		
	}

}
																						