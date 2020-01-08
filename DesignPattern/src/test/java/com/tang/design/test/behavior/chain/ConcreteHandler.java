package com.tang.design.test.behavior.chain;
/**
 * 
 * <p>2020年1月8日</p>
 * @author tangxing
 * <p> 处理器1 </p>
 */
public class ConcreteHandler extends Handler {

	@Override
	public void handlerRequest(String request) {
		if("one".equals(request)) {
			System.out.println("处理器1处理请求...");
		}else {
			if(getNext() != null) {
				getNext().handlerRequest(request);
			}else {
				System.out.println("没有处理器处理请求~");
			}
		}
		
	}

}
