package com.tang.design.test.struct.decorater;

public abstract class Decorator implements Component {
	
	private final Component component;
	
	public Decorator(Component component) {
		super();
		this.component = component;
	}

	@Override
	public void operator() {
		component.operator();
	}

}
