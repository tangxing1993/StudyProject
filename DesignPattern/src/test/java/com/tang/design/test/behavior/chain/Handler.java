package com.tang.design.test.behavior.chain;
/**
 * 
 * <p>2020年1月8日</p>
 * @author tangxing
 * <p> 抽象责任处理接口 </p>
 */
public abstract class Handler {
	
	// 下一个任务执行器
	private Handler next;

	public Handler getNext() {
		return next;
	}

	public void setNext(Handler next) {
		this.next = next;
	}
	
	// 处理请求
	public abstract void handlerRequest(String request);
	
}
