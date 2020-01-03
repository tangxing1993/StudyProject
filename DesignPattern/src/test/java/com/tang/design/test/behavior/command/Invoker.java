package com.tang.design.test.behavior.command;
/**
 * 
 * <p>2020年1月3日</p>
 * @author tangxing
 * <p> 调用者 </p>
 */
public class Invoker {

	private Command command;

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void call() {
		System.out.println("调用者这行命令...");
		command.execute();
	}	
	
	
}
