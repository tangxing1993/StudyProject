package org.tang.Activiti.test;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date   2019年11月18日
 * @author tangxing
 * @desc   <p> 用户任务采用用户角色组测试 </p>
 */
@Slf4j
public class UserGroupTaskTest extends AbstractProcessTest {

	/**
	 * 
	 * @date 2019年11月18日
	 * @desc <p> 流程部署 </p>
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeployment()
			.name("用户角色组测试")
			.addClasspathResource("processes/UserGroupTask.bpmn")
			.addClasspathResource("processes/UserGroupTask.png")
			.deploy();
		log.info("--- 部署成功 ---");
		this.deployPrint(deploy);
		/**
		 * 添加角色用户组
		 */
		IdentityService identityService = this.processEngine.getIdentityService();
		User user = identityService.newUser("张三");
		user.setFirstName("张");
		user.setLastName("三");
		identityService.saveUser(user);
		Group group = identityService.newGroup("部门经理");
		group.setName("部门经理");
		identityService.saveGroup(group);
		identityService.createMembership(user.getId(), group.getId());
	}
	
	/**
	 * 
	 * @date 2019年11月18日
	 * @desc <p> 启动流程实例 </p>
	 */
	@Test
	public void testStartProcessInstance() {
		String processDefinitionKey = "UserGroupTask";
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
			.startProcessInstanceByKey(processDefinitionKey);
		this.processInstancePrint(processInstance);
	}

	/**
	 * 
	 * @date 2019年11月18日
	 * @desc <p> 张三执行用户任务 </p>
	 */
	@Test
	public void testCommitUserTask() {
		Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.taskCandidateGroup("部门经理")
			.singleResult();
		this.taskPrint(task);
		// 认领任务
		this.processEngine.getTaskService()
			.claim(task.getId(), "张三");
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("--- 任务执行成功 ---");
	}
	
	/**
	 * 
	 * @date 2019年11月18日
	 * @desc <p> 删除流程 </p>
	 */
	@Test
	public void testDeleteProcess() {
		Deployment deploy = this.processEngine.getRepositoryService()
			.createDeploymentQuery()
			.processDefinitionKey("UserGroupTask")
			.singleResult();
		this.processEngine.getRepositoryService()
			.deleteDeployment(deploy.getId(), true);
		log.info("--- 删除流程成功 ---");
	}
	
}

