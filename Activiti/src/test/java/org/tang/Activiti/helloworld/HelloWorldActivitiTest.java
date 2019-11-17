package org.tang.Activiti.helloworld;

import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.tang.Activiti.test.AbstractProcessTest;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date   2019年11月16日
 * @author tangxing
 * @desc   <p> Helloword流程引擎出门测试 </p>
 */
@Slf4j
public class HelloWorldActivitiTest extends AbstractProcessTest{

	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 部署流程图 </p>
	 */
	@Test
	public void testDeployProcessEngine() {
		Deployment deploy = processEngine.getRepositoryService() // 流程图存储实例
					 .createDeployment()     // 创建一个部署实例
					 .name("Helloworld入门实例")
					 .addClasspathResource("processes/Helloworld.bpmn") // 添加类路径下的流程图
					 .addClasspathResource("processes/Helloworld.png")
					 .deploy();  // 部署
		log.info("流程定义Id: {} " ,deploy.getId());
		log.info("流程定义Key: {} " ,deploy.getKey());
		log.info("流程定义名称: {} " ,deploy.getName());
		log.info("流程定义部署时间: {} " ,deploy.getDeploymentTime());
	}
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 启动流程 </p>
	 */
	@Test
	public void testStartProcess() {
		ProcessInstance processInstance = processEngine.getRuntimeService()  // 流程的运行时对象
					.startProcessInstanceByKey("helloworld");
		log.info("流程定义Id: {} ", processInstance.getProcessDefinitionId());			 
		log.info("流程定义Key: {} ", processInstance.getProcessDefinitionKey());			 
		log.info("流程定义Name: {} ", processInstance.getProcessDefinitionName());			 
		log.info("流程定义Version: {} ", processInstance.getProcessDefinitionVersion());			 
		log.info("流程实例Id: {} ", processInstance.getProcessInstanceId());			 
	}
	
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 查询第一个人的任务 张三 </p>
	 */
	@Test
	public void testFirstTask() {
		List<Task> list = getTask("2501","张三");
		list.stream().forEach(task -> {
			log.info("Task: {} ", task);
		});
	}
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p>提交张三任务 </p>
	 */
	@Test
	public void testFirstComplete() {
		Task task = getTask("2501","张三").get(0);
		processEngine.getTaskService()
		 .complete(task.getId());    
		log.info("提交张三任务");
	}
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 获取李四任务 </p>
	 */
	@Test
	public void testSecondTask() {
		List<Task> list = getTask("2501","李四");
		list.stream().forEach(task -> {
			log.info("Task: {} ", task);
		});
	}
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 提交李四任务 </p>
	 */
	@Test
	public void testSecondComplete() {
		Task task = getTask("2501","李四").get(0);
		processEngine.getTaskService()
		 .complete(task.getId());    
		log.info("提交李四任务");
	}
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 获取总经理王五任务 </p>
	 */
	@Test
	public void testThreeTask() {
		List<Task> list = getTask("2501","王五");
		list.stream().forEach(task -> {
			log.info("Task: {} ", task);
		});
	}
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 提交王五任务 </p>
	 */
	@Test
	public void testThreeComplete() {
		Task task = getTask("2501","王五").get(0);
		processEngine.getTaskService()
		 .complete(task.getId());    
		log.info("提交王五任务");
	}
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 流程历史 </p>
	 */
	@Test
	public void testProcessHistory() {
		processEngine.getHistoryService()  // 获取历史服务
					 .createHistoricTaskInstanceQuery()
					 .processInstanceId("2501")
					 .list()
					 .stream()
					 .forEach(System.out::println);
	}
	

	private List<Task> getTask(String instanceId, String userId) {
		List<Task> list = processEngine.getTaskService()    // 获取任务服务对象
					 .createTaskQuery()   // 创建一个任务查询器
					 .processInstanceId(instanceId)
					 .taskAssignee(userId)
					 .list();
		return list;
	}
	
}
