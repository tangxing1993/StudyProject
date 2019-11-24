package org.tang.activiti.demo.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.tang.activiti.demo.domain.LeaveBill;

/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 工作流服务接口 </p>
 */
public interface WorkflowService {
	
	/**
	 * 
	 * @date 2019年11月21日
	 * @desc <p> 部署流程图 </p>
	 * @param imageName   流程名称
	 * @param inputStream zip文件流
	 */
	void deployProcess(String imageName, InputStream inputStream);
	/**
	 * 
	 * @date 2019年11月22日
	 * @desc <p> 获取部署列表 </p>
	 * @return
	 */
	List<Deployment> listDeploy();
	/**
	 * 
	 * @date 2019年11月22日
	 * @desc <p> 查询流程定义列表 </p>
	 * @return
	 */
	List<ProcessDefinition> listProcessDefinition();
	/**
	 * 
	 * @date 2019年11月22日
	 * @desc <p> 获取流程图的工作流 </p>
	 * @param processDefinitionId 流程定义Id
	 * @return
	 */
	InputStream getProcessDefinitionInputStream(String processDefinitionId);
	/**
	 * 
	 * @date 2019年11月22日
	 * @desc <p> 删除流程定义 </p>
	 * @param deploymentId
	 */
	void deleteProcessDefinition(String deploymentId);
	/**
	 * 
	 * @date 2019年11月24日
	 * @desc <p> 启动请假流程 </p>
	 */
	void startLeaveBillProcess(Integer leaveBillId);
	/**
	 * 
	 * @date 2019年11月24日
	 * @desc <p> 获取用户的任务列表 </p>
	 * @param loginUserName
	 */
	List<Task> listTaskByUserName(String loginUserName);
	/**
	 * 
	 * @date 2019年11月24日
	 * @desc <p> 通过任务获取请假单信息 </p>
	 * @param taskId
	 * @return
	 */
	LeaveBill findLeaveBillByTaskId(String taskId);
	/**
	 * 
	 * @date 2019年11月24日
	 * @desc <p> 获取连线信息 </p>
	 * @param taskId
	 * @return
	 */
	List<String> findFlowSequenceByTaskId(String taskId);
	
}
