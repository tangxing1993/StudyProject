package org.tang.Activiti.test;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>2019年11月18日</p>
 * @author tangxing
 * <p> 测试用户任务的执行用户组在任务监听器中设置 </p>
 */
@Slf4j
public class CandidateUsersListenerTaskTest extends AbstractProcessTest{

	/**
	 * 
	 * <p>2019年11月18日</p>
	 * @author tangxing
	 * <p> 自定义的任务监听器 </p>
	 */
	public static class MyTaskListener implements TaskListener{

		private static final long serialVersionUID = 1L;

		@Override
		public void notify(DelegateTask delegateTask) {
			delegateTask.addCandidateUser("鲁智深");
			delegateTask.addCandidateUser("晁盖");
			delegateTask.addCandidateUser("林冲");
			delegateTask.addCandidateUser("吴用");
			delegateTask.addCandidateUser("时迁");
		}
		
	}
	
	/**
	 * 部署流程
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeployment()
			.name("任务监听器中设置用户组")
			.addClasspathResource("processes/CandidateUsersListenerTask.bpmn")
			.addClasspathResource("processes/CandidateUsersListenerTask.png")
			.deploy();
		this.deployPrint(deploy);
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void testStartProcessInstance() {
		String processDefinitionKey = "CandidateUsersListenerTask";
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey(processDefinitionKey);
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 执行用户任务  吴用
	 */
	@Test
	public void testCommitUserTask() {
		Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.taskCandidateUser("吴用")
			.singleResult();
		this.taskPrint(task);
		// 认领任务
		this.processEngine.getTaskService()
			.claim(task.getId(), "吴用");
		// 执行任务
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("--- 任务执行成功 ---");
	}
	
}
