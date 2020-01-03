package com.tang.design.test.behavior.command;
/**
 * 
 * <p>2020年1月3日</p>
 * @author tangxing
 * <p> 具体命令的实现 </p>
 */
public class ConcreteCommand implements Command {

	private final Receiver receiver;
	
	public ConcreteCommand() {
		this.receiver = new Receiver();
	}

	@Override
	public void execute() {
		receiver.action();
	}

}
