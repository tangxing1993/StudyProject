package org.tang.oa.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.core.env.Environment;
import org.tang.oa.system.service.DepartmentService;
import org.tang.oa.system.service.RoleService;
import org.tang.oa.system.service.UserService;

/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 通用的控制器 </p>
 */
public abstract class BaseController implements MessageSourceAware,EnvironmentAware {

	protected MessageSource messageSource;
	
	protected Environment environment;
	
	@Autowired
	protected RoleService roleService;
	
	@Autowired
	protected DepartmentService departmentService;
	
	@Autowired
	protected UserService userService;
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	

	
	
}
