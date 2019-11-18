package org.tang.Activiti.test;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>2019年11月18日</p>
 * @author tangxing
 * <p> 接受任务测试 </p>
 */
@Slf4j
public class ReceiveTakTest extends AbstractProcessTest {

	/**
	 * 部署流程
	 */
	@Test
	public void testDeploy() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Deployment deploy = repositoryService
			.createDeployment()
			.addClasspathResource("processes/ReceiveTask.bpmn")
			.addClasspathResource("processes/ReceiveTask.png")
			.name("接收类型的任务")
			.deploy();
		log.info("--- 部署成功 ---");
		deployPrint(deploy);
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void testStartProcessInstant() {
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey("ReceiveTask");
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 提交统计任务
	 */
	@Test
	public void testCommitAggregationTask() {
		// 获取当前接受任务执行器
		Execution execution = this.processEngine.getRuntimeService()
			.createExecutionQuery()
			.processDefinitionKey("ReceiveTask")
			.activityId("receivetask1")
			.singleResult();
		log.info("Execution: {}", execution);
		// 设置当日执行额
		this.processEngine.getRuntimeService().setVariable(execution.getId(), "total", 30000);
		// 向后推进一步 (5.0 使用的是signal() 6.0 使用trigger())
		this.processEngine.getRuntimeService().trigger(execution.getId());
		log.info("统计销售额成功");
	}
	
	/**
	 * 给老板发消息
	 */
	@Test
	public void testCommitSendBossMessageTask() {
		// 获取给老板发短信的执行任务
		Execution singleResult = this.processEngine.getRuntimeService().createExecutionQuery()
			.processDefinitionKey("ReceiveTask")
			.activityId("receivetask2")
			.singleResult();
		int total =	(int) this.processEngine.getRuntimeService().getVariable(singleResult.getId(), "total");
		log.info("当日的销售额为: {} " , total);
		this.processEngine.getRuntimeService().trigger(singleResult.getId());
	}
	
}
