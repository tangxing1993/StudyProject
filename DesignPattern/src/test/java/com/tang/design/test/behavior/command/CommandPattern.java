package com.tang.design.test.behavior.command;
/**
 * 
 * <p>2020年1月3日</p>
 * @author tangxing
 * <p> 命令模式案例 </p>
 */
public class CommandPattern {

	public static void main(String[] args) {
		Invoker invoker = new Invoker();
		invoker.setCommand(new ConcreteCommand());
		System.out.println("开始执行命令调用~");
		invoker.call();
	}
	
}
