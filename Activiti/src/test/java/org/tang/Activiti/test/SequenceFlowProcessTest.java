package org.tang.Activiti.test;

import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Maps;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date   2019年11月17日
 * @author tangxing
 * @desc   <p> 连线流程测试 </p>
 */
@Slf4j
public class SequenceFlowProcessTest extends AbstractProcessTest{
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> </p>
	 */
	@Test
	public void testDeploy() {
		Deployment deploy = processEngine.getRepositoryService()
					 .createDeployment()
					 .addClasspathResource("processes/SequenceFlow.bpmn")
					 .addClasspathResource("processes/SequenceFlow.png")
					 .name("连线案例")
					 .deploy();
		deployPrint(deploy);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 启动流程实例 </p>
	 */
	@Test
	public void testStartProcess() {
		ProcessInstance processInstance = processEngine.getRuntimeService()
					 .startProcessInstanceByKey("SequeneceFlow");
		processInstancePrint(processInstance);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 提交第一个任务，并设置变量为不重要 </p>
	 */
	@Test
	public void testSequenceFlowCondition() {
		List<Task> list = processEngine.getTaskService().createTaskQuery().list();
		list.forEach( task -> {
			processEngine.getTaskService().complete(task.getId(), Maps.newHashMap("message", "不重要"));
		});
	}
	
	/**
	 * 	
	 * @date 2019年11月17日
	 * @desc <p> 删除部署 </p>
	 */
	@Test
	public void testDeleteDeploy() {
		 Deployment deploy = processEngine.getRepositoryService()
		 			  .createDeploymentQuery()
		 			  .processDefinitionKey("SequeneceFlow")
		 			  .singleResult();
		processEngine.getRepositoryService()
					 .deleteDeployment(deploy.getId(), true);
		log.info("删除成功");
	}
	
}
