package org.tang.Activiti.test;

import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.assertj.core.util.Maps;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date   2019年11月17日
 * @author tangxing
 * @desc   <p> 运行时流程测试 </p>
 */
@Slf4j
public class RuntimeProcessTest extends AbstractProcessTest{

	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 测试流程启动一个实例 </p>
	 */
	@Test
	public void testProcessStart() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService()
					 .createProcessDefinitionQuery()
					 .processDefinitionKey("leaveprocess")
					 .latestVersion()
					 .singleResult();
		ProcessInstance processInstance = processEngine.getRuntimeService()
					 .startProcessInstanceById(processDefinition.getId());       // 启动一个流程实例通过流程定义Id
					 //.startProcessInstanceById(processDefinitionId, variables)   // 启动一个流程实例通过流程定义id和流程变量
					 //.startProcessInstanceByKey(processDefinitionKey)     // 启动一个流程实例通过流程定义的key
					 //.startProcessInstanceByKey(processDefinitionKey, variables)  // 启动一个流程实例通过流程定义的key和流程变量
		
		log.info("流程实例启动成功");
		processInstancePrint(processInstance);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 正在运行的流程实例查询: 对应数据库【ACT_RU_EXECUTION】 </p>
	 */
	@Test
	public void testProcessInstanceQuery() {
		processEngine.getRuntimeService()
					 .createProcessInstanceQuery()
					 .list()
					 .forEach(processInstance -> {
						 this.processInstancePrint(processInstance);
					 });
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 流程实例历史查询  对应数据库: 【ACT_HI_PRCOINST】</p>
	 */
	@Test
	public void testProcessInstanceHistoryQuery() {
		processEngine.getHistoryService()
					 .createHistoricProcessInstanceQuery()
					 .list()
					 .forEach(processInstance -> {
						 historyProcessInstancePrint(processInstance);
					 });
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 正在执行的任务查询 对应数据库: 【ACT_RU_TASK】 </p>
	 */
	@Test
	public void testTaskQuery() {
		processEngine.getTaskService()
					 .createTaskQuery()
					 .list()
					 .forEach(task -> {
						 taskPrint(task);
					 });
	}

	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 历史任务查询 对应数据库【】 </p>
	 */
	@Test
	public void testHistoryTaskQuery() {
		processEngine.getHistoryService()
					 .createHistoricTaskInstanceQuery()
					 .list()
					 .forEach( historyTask -> {
						 historyTaskPrint(historyTask);
					 });
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 测试任务变量 对应数据库: ACT_RU_VARIABLE   【流程变量为了解决流程中多条线的情况】 </p>
	 */
	@Test
	public void testSetTaskVariables() {
		Task task = processEngine.getTaskService()
						 .createTaskQuery()
						 .singleResult();
		// 提交任务
		processEngine.getTaskService()
					 .complete(task.getId(), Maps.newHashMap("请假原因", "生病"));
		log.info("任务已提交：{}", task.getAssignee());
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 获取任务变量 </p>
	 */
	@Test
	public void testGetTaskVariables() {
		Task task = processEngine.getTaskService()
				 .createTaskQuery()
				 .singleResult();
		processEngine.getTaskService().getVariableInstances(task.getId()).forEach( (key,value) -> {
			log.info("Key: {} -> Value: {}", key, value);
		});;
	}

	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 任务历史变量查询 对应数据库: 【ACT_HI_VARINST】 </p>
	 */
	@Test
	public void testHistoryVariableQuery() {
		processEngine.getHistoryService()
					 .createHistoricVariableInstanceQuery()
					 .list()
					 .forEach( variable -> {
						 log.info("HistoryVariable: {} ", variable);
					 });
	}
	
}
