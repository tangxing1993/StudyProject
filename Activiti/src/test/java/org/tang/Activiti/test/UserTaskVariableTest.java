package org.tang.Activiti.test;

import java.util.Map;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Maps;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>2019年11月18日</p>
 * @author tangxing
 * <p> 流程受理人使用流程变量 Assignee=${user}</p>
 */
@Slf4j
public class UserTaskVariableTest extends AbstractProcessTest{
	
	/**
	 * 部署流程
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeployment()
			.name("流程受理人使用变量")
			.addClasspathResource("processes/UserTaskVariable.bpmn")
			.addClasspathResource("processes/UserTaskVariable.png")
			.deploy();
		log.info("--- 流程部署成功 ---");
		this.deployPrint(deploy);
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void testStartProcessInstance() {
		String 	processDefinitionKey = "UserTaskVariable";
		// 设置流程变量User=李四
		Map<String, Object> variables = Maps.newHashMap("user", "李四");
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey(processDefinitionKey,variables);
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 李四提交任务
	 */
	@Test
	public void testCommitUserTask() {
		Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.taskAssignee("李四")
			.singleResult();
		this.taskPrint(task);
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("{}提交任务成功", task.getAssignee());
	}
	
}
