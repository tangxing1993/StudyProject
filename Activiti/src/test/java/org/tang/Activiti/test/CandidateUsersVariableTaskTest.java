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
 * <p> 测试用户任务的执行人的用户组为流程变量 </p>
 */
@Slf4j
public class CandidateUsersVariableTaskTest extends AbstractProcessTest{
	
	/**
	 * 部署流程
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeployment()
			.name("执行人用户组为流程变量")
			.addClasspathResource("processes/CandidateUsersVariableTask.bpmn")
			.addClasspathResource("processes/CandidateUsersVariableTask.png")
			.deploy();
		this.deployPrint(deploy);
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void testStartProcessInstance() {
		String processDefinitionKey = "CondidateUsersVariableTask";
		Map<String, Object> variables = Maps.newHashMap("users", "小鱼儿,花无缺,邀月");
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey(processDefinitionKey, variables);
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 执行流程任务
	 */
	@Test
	public void testCommitUserTask() {
		Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.taskCandidateUser("邀月")
			.singleResult();
		this.taskPrint(task);
		// 设置任务执行人
		this.processEngine.getTaskService()
			.setAssignee(task.getId(), "邀月");
		// 执行任务
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("任务执行成功");
	}
	
}
