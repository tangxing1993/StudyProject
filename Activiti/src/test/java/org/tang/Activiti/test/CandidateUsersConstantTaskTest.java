package org.tang.Activiti.test;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>2019年11月18日</p>
 * @author tangxing
 * <p> 候选人为用户组测试: 在绘制流程图时设置Assignee为空,添加Condidate Users : 张三,李四,王五,陈六 </p>
 */
@Slf4j
public class CandidateUsersConstantTaskTest extends AbstractProcessTest{

	/**
	 * 部署流程图
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeployment()
			.name("候选人为常量用户组")
			.addClasspathResource("processes/CandidateUsersConstantTask.bpmn")
			.addClasspathResource("processes/CandidateUsersConstantTask.png")
			.deploy();
		this.deployPrint(deploy);
	}
	
	/**
	 * 启动流程    
	 * 启动成功后可看到  ACT_RU_IDENTITYLINK表中每个候选人都提供了一个TYPE为participant的参与者
	 */
	@Test
	public void testStartProcessInstance() {
		String processDefinitionKey = "CondidateUsersConstantTask";
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey(processDefinitionKey);
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 提交用户任务(可使用张三,李四，王五，陈六提交任务)
	 */
	@Test
	public void testCommitUserTask() {
		// 使用李四获取任务,陈六提交任务
		Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.taskCandidateUser("李四")   // 包含候选人xxx的任务
			.singleResult();
		this.taskPrint(task);
		// 设置执行人为陈六
		this.processEngine.getTaskService()
			.setAssignee(task.getId(), "陈六");
		// 提交任务
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("--- 任务已提交 ---");
	}
	
	/**
	 * 删除流程定义
	 */
	@Test
	public void testDeleteProcessDefinition() {
		DeploymentQuery deploymentQuery = this.processEngine.getRepositoryService()
			.createDeploymentQuery()
			.processDefinitionKey("CondidateUsersConstantTask");
		this.processEngine.getRepositoryService()
			.deleteDeployment(deploymentQuery.singleResult().getId(), true);
		log.info("--- 删除成功 ---");
	}
	
}
