package org.tang.Activiti.test;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date   2019年11月17日
 * @author tangxing
 * @desc   <p> 并行网关测试 </p>
 */
@Slf4j
public class ParallelGateWayTest extends AbstractProcessTest {

	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 部署并行网关 </p>
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = this.processEngine.getRepositoryService()
						  .createDeployment()
						  .name("并行网关")
						  .addClasspathResource("processes/ParallelGateWay.bpmn")
						  .addClasspathResource("processes/ParallelGateWay.png")
						  .deploy();
		log.info("--- 部署成功 ---");
		this.deployPrint(deploy);
	}
	
	/**
	 *
	 * @date 2019年11月17日
	 * @desc <p> 启动并行网关实例 </p>
	 */
	@Test
	public void testStartParallelProcess() {
		String processDefinitionKey = "ParallelGateWay";
		ProcessInstance processInstance = this.processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey);
		this.processInstancePrint(processInstance);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 买家执行付款任务 </p>
	 */
	@Test
	public void testBuyerCommitPayTask() {
		Task task = this.processEngine.getTaskService()
			.createTaskQuery()
			.taskAssignee("买家")
			.singleResult();
		this.taskPrint(task);
		this.processEngine.getTaskService()
			.complete(task.getId());
		log.info("-- 买家已付款 ---");
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 商家执行发货任务 </p>
	 */
	@Test
	public void testBussinesserCommitShipTask() {
		Task task = this.processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee("商家")
				.orderByTaskCreateTime().asc()
				.list()
				.get(0);
		this.processEngine.getTaskService()
		.complete(task.getId());
		log.info("-- 商家已发货 ---");
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 买家执行收货任务 </p>
	 */
	@Test
	public void testBuyerCommitReceiptTask() {
		Task task = this.processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee("买家")
				.singleResult();
			this.taskPrint(task);
			this.processEngine.getTaskService()
				.complete(task.getId());
			log.info("-- 买家已收货 ---");
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 商家执行收款任务 </p>
	 */
	@Test
	public void testBuissinesserCommitReciptTask() {
		Task task = this.processEngine.getTaskService()
				.createTaskQuery()
				.taskAssignee("商家")
				.orderByTaskCreateTime().asc()
				.list()
				.get(0);
		this.processEngine.getTaskService()
		.complete(task.getId());
		log.info("-- 商家已收款 ---");
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 删除流程 </p>
	 */
	@Test
	public void testDeleteDeploy() {
		Deployment deployment = this.processEngine.getRepositoryService().
				createDeploymentQuery().processDefinitionKey("ParallelGateWay").singleResult();
		this.processEngine.getRepositoryService().deleteDeployment(deployment.getId(), true);
		log.info("流程删除成功");
	}
	
	
}
