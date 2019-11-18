package org.tang.Activiti.test;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>2019年11月18日</p>
 * @author tangxing
 * <p> 用户任务受理人为固定值  (流程图中UserTask Assignee 为张三) </p>
 */
@Slf4j
public class UserTaskByConstantTest extends AbstractProcessTest{

	/**
	 * 部署流程
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeployment()
			.name("受理人为常量")
			.addClasspathResource("processes/UserTaskByConstant.bpmn")
			.addClasspathResource("processes/UserTaskByConstant.png")
			.deploy();
		this.deployPrint(deploy);
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void testStartProcessInstant() {
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey("UserTaskConstant");
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 张三执行任务
	 */
	@Test
	public void testCommitUserTask() {
		Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.taskAssignee("张三")
			.singleResult();
		this.taskPrint(task);
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("{}执行任务成功", task.getAssignee());
	}
	
}
