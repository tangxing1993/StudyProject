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
 * <p> 流程执行人在监听器中设置 : 绘制流程图时在userTask的listener中添加MyUserTaskListener,Assignee置空</p>
 */
@Slf4j
public class UserTaskListenerTest extends AbstractProcessTest{

	/**
	 * 
	 * <p>2019年11月18日</p>
	 * @author tangxing
	 * <p> 自定义的任务监听器: 在监听器中设置执行人 </p>
	 */
	public static class MyUserTaskListener implements TaskListener{

		private static final long serialVersionUID = 1L;

		@Override
		public void notify(DelegateTask delegateTask) {
			delegateTask.setAssignee("王五");
		}
	}
	/**
	 * 部署流程
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeployment()
			.name("监听器中设置执行人")
			.addClasspathResource("processes/UserTaskListener.bpmn")
			.addClasspathResource("processes/UserTaskListener.png")
			.deploy();
		this.deployPrint(deploy);
	}
	
	/**
	 * 启动流程实例
	 */
	@Test
	public void testStartProcessInstance() {
		String processDefinitionKey = "UserTaskListener";
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey(processDefinitionKey);
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 王五执行提交任务 （王五在任务监听器中被设置为执行人）
	 */
	@Test
	public void testCommitUserTask() {
		Task task = this.processEngine
			.getTaskService()
			.createTaskQuery()
			.taskAssignee("王五")
			.singleResult();
		this.taskPrint(task);
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("--- {}执行任务成功 ---", task.getAssignee());
	}
	
	
	
}

