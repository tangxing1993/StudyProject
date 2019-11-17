package org.tang.Activiti.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.After;
import org.junit.Before;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @date   2019年11月17日
 * @author tangxing
 * @desc   <p> 抽象的流程测试类 </p>
 */
@Slf4j
public abstract class AbstractProcessTest {


	protected ProcessEngine processEngine;
	
	@Before
	public void before() {
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		// 设置参数
		configuration.setJdbcUrl("jdbc:mysql://192.168.1.18:3306/process_engine?useSSl=false&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false");
		configuration.setJdbcDriver("com.mysql.jdbc.Driver");
		configuration.setJdbcUsername("root");
		configuration.setJdbcPassword("123456");
		// 设置数据表创建
		configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 构建工作流引擎
		processEngine = configuration.buildProcessEngine();
		log.info("ProcessEngineName: {}", processEngine.getName());
	}
	
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 流程部署打印 </p>
	 * @param deploy
	 */
	protected void deployPrint(Deployment deploy) {
		log.info("流程部署Id: {}" , deploy.getId());
		log.info("流程部署key: {}" , deploy.getKey());
		log.info("流程部署Name: {}" , deploy.getName());
		log.info("流程部署Category: {}" , deploy.getCategory());
		log.info("流程部署DeploymentTime: {}" , deploy.getDeploymentTime());
		log.info("流程部署TenantId: {}" , deploy.getTenantId());
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 流程实例打印 </p>
	 * @param processInstance
	 */
	protected void processInstancePrint(ProcessInstance processInstance) {
		log.info("流程执行Id: {}" , processInstance.getId());
		log.info("流程部署Id: {}" , processInstance.getDeploymentId());
		log.info("流程定义Id: {}" , processInstance.getProcessDefinitionId());
		log.info("流程实Starttime: {}" , processInstance.getStartTime());
		log.info("流程实例Id: {}" , processInstance.getProcessInstanceId());
		log.info("流程启动用户Id: {}", processInstance.getStartUserId());
		log.info("流程活动Id: {}", processInstance.getActivityId());
		log.info("流程实例Description: {}", processInstance.getDescription());
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 流程实例历史打印 </p>
	 * @param processInstance
	 */
	protected void historyProcessInstancePrint(HistoricProcessInstance processInstance) {
		log.info("流程实例Id: {} ", processInstance.getId());
		 log.info("流程实例Name: {} ", processInstance.getName());
		 log.info("流程实例EndTime: {} ", processInstance.getEndTime());
		 log.info("流程实例DeleteReason: {} ", processInstance.getDeleteReason());
		 log.info("流程实例DurationInMillis: {} ", processInstance.getDurationInMillis());
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 任务打印 </p>
	 * @param task
	 */
	protected void taskPrint(Task task) {
		log.info("任务Id: {}", task.getId());
		 log.info("任务Name: {}", task.getName());
		 log.info("任务CreateTime: {}", task.getCreateTime());
		 log.info("任务DueDate: {}", task.getDueDate());
		 log.info("任务Assignee: {}", task.getAssignee());
		 log.info("任务Category: {}", task.getCategory());
		 log.info("任务ClaimTime: {}", task.getClaimTime());
		 log.info("任务ExecutionId: {}", task.getExecutionId());
		 log.info("任务Formkey: {}", task.getFormKey());
		 log.info("任务Owner: {}", task.getOwner());
		 log.info("任务ParentTaskId: {}", task.getParentTaskId());
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 历史任务打印 </p>
	 * @param historyTask
	 */
	protected void historyTaskPrint(HistoricTaskInstance historyTask) {
		log.info("历史任务Id: {} ", historyTask.getId());
		 log.info("历史任务Assignee: {} ", historyTask.getAssignee());
		 log.info("历史任务Category: {} ", historyTask.getCategory());
		 log.info("历史任务ClaimTime: {} ", historyTask.getClaimTime());
		 log.info("历史任务CreateTime: {} ", historyTask.getCreateTime());
		 log.info("历史任务DeleteReason: {} ", historyTask.getDeleteReason());
		 log.info("历史任务DueDate: {} ", historyTask.getDueDate());
		 log.info("历史任务DurationInMillis: {} ", historyTask.getDurationInMillis());
		 log.info("历史任务EndTime: {} ", historyTask.getEndTime());
		 log.info("历史任务ExecutionId: {} ", historyTask.getExecutionId());
	}
	
	
	@After
	public void after() {
		processEngine.close();
	}
	
}
