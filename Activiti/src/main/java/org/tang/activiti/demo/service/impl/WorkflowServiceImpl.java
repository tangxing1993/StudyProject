package org.tang.activiti.demo.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.transaction.Transactional;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.activiti.demo.service.WorkflowService;
/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 工作流服务实现 </p>
 */
@Service
public class WorkflowServiceImpl implements WorkflowService {

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
	
}
