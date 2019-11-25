package org.tang.activiti.demo.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.transaction.Transactional;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tang.activiti.demo.domain.LeaveBill;
import org.tang.activiti.demo.service.LeaveBillService;
import org.tang.activiti.demo.service.WorkflowService;
import org.tang.activiti.demo.util.SessionContext;
/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 工作流服务实现 </p>
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {
	
	private static final String BUSSINESS_KEY_SPLIT = ":";

	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected IdentityService identityService;
	@Autowired
	protected FormService formServcie;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	private LeaveBillService leaveBillServcie;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void deployProcess(String imageName, InputStream inputStream) {
		repositoryService.createDeployment()
						  .name(imageName)
						  .addZipInputStream(new ZipInputStream(inputStream))
						  .deploy();
	}

	@Override
	public List<Deployment> listDeploy() {
		return repositoryService.createDeploymentQuery()
				  .orderByDeploymenTime()
				  .desc()
				  .list();
	}

	@Override
	public List<ProcessDefinition> listProcessDefinition() {
		return repositoryService.createProcessDefinitionQuery()
								 .orderByProcessDefinitionVersion()
								 .desc()
								 .list();
	}

	@Override
	public InputStream getProcessDefinitionInputStream(String processDefinitionId) {
		return repositoryService.getProcessDiagram(processDefinitionId);
	}

	@Override
	public void deleteProcessDefinition(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, Boolean.TRUE);
	}

	/**
	 * 启动流程：
	 * 1. 更新请假单状态   0 -> 1
	 * 2. 绑定启动用户
	 * 3. 将流程与业务绑定 (ACT_TU_EXECUTION -> BussinessKey)
	 * 4. 启动  
	 */
	@Override
	public void startLeaveBillProcess(Integer leaveBillId) {
		LeaveBill leaveBill = leaveBillServcie.findById(leaveBillId).get();
		leaveBill.setState(LeaveBill.STATE_AUDIT);
		String processDefinitionKey = LeaveBill.class.getSimpleName();
		String businessKey = processDefinitionKey + BUSSINESS_KEY_SPLIT +  leaveBill.getId();
		Map<String, Object> variables = new HashMap<>();
		variables.put("input_user", SessionContext.getLoginUserName());
		runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
		leaveBillServcie.save(leaveBill);
	}

	@Override
	public List<Task> listTaskByUserName(String loginUserName) {
		return taskService.createTaskQuery()
				   .taskAssignee(loginUserName)
				   .orderByTaskCreateTime()
				   .asc()
				   .list();
	}

	@Override
	public LeaveBill findLeaveBillByTaskId(String taskId) {
		Task task = taskService.createTaskQuery()
				   .taskId(taskId)
				   .singleResult();
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					  .processInstanceId(processInstanceId)
					  .singleResult();
		String businessKey = processInstance.getBusinessKey();
		String leaveBillId = businessKey.split(BUSSINESS_KEY_SPLIT)[1];
		return leaveBillServcie.findById(Integer.parseInt(leaveBillId)).get();
	}

	/**
	 *  * 获取当前激活节点的后续连线
	 *  	- 获取当前的任务节点
	 *  	- 根据任务节点获取当前的执行器
	 *  	- 由执行器获取当前激活的活动节点标识
	 *  	- 获取Bpm定义图并根据活动节点找到当前的活动元素对象
	 *  	- 获取任务的输出连线
	 */
	@Override
	public List<String> findFlowSequenceByTaskId(String taskId) {
		List<String> flows = new ArrayList<>();
		Task task = taskService.createTaskQuery()
				   .taskId(taskId)
				   .singleResult();
		String executionId = task.getExecutionId();
		Execution execution = runtimeService.createExecutionQuery()
					  .executionId(executionId)
					  .singleResult();
		String processDefinitionId = task.getProcessDefinitionId();
		// TODO 获取当前任务节点的连接线属性
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		// 获取当前激活流程的节点
		FlowNode flowElement = (FlowNode) bpmnModel.getFlowElement(execution.getActivityId());
		List<SequenceFlow> sequenceFlows = flowElement.getOutgoingFlows();
		for(SequenceFlow sequenceFlow : sequenceFlows) {
			String name = "批准";
			if(!StringUtils.isEmpty(sequenceFlow.getName())) {
				name = sequenceFlow.getName();
			}
			flows.add(name);
		}
		return flows;
	}

	@Override
	public void saveProcessTask(String taskId, String outcome,String content) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		// 添加批注
		taskService.addComment(taskId, task.getProcessInstanceId(), content);
		// 完成任务
		Map<String, Object> variables = new HashMap<>();
		variables.put("outcome", outcome);
		taskService.complete(taskId, variables);
		// 任务流程完毕后设置状态
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		if(processInstance == null) {
			LeaveBill leaveBill = findLeaveBillByTaskId(taskId);
			leaveBill.setState(LeaveBill.STATE_COMPLETE);
			leaveBillServcie.save(leaveBill);
		}
	}

	@Override
	public List<Comment> listLeaveComment(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		return taskService.getProcessInstanceComments(task.getProcessInstanceId());
	}
	
}
