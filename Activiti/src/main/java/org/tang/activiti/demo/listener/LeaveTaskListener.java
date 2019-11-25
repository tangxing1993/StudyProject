package org.tang.activiti.demo.listener;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tang.activiti.demo.service.EmployeeService;
import org.tang.activiti.demo.util.SessionContext;
/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 请假单的任务监听器 </p>
 */
public class LeaveTaskListener implements TaskListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		// 设置下一个任务的执行人
		String loginUserName = SessionContext.getLoginUserName();
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		EmployeeService employeeService = applicationContext.getBean(EmployeeService.class);
		String assignee = employeeService.getOneByName(loginUserName).get().getManager().getName();
		delegateTask.setAssignee(assignee);
	}

}
