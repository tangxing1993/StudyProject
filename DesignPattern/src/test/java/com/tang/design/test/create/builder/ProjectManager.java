package com.tang.design.test.create.builder;
/**
 * 
 * <p>2019年11月20日</p>
 * @author tangxing
 * <p> 项目负责人 </p>
 */
public class ProjectManager {

	private final Decorator decorator;

	public ProjectManager(Decorator decorator) {
		super();
		this.decorator = decorator;
	}
	
	/**
	 *  完成对象的构建
	 * @return
	 */
	public Parlour getParlour() {
		this.decorator.buildWall();
		this.decorator.buildTV();
		this.decorator.buildSofa();
		return this.decorator.getParlour();
	}
	
}
