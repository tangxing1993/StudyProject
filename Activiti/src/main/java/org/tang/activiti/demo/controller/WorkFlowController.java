package org.tang.activiti.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.tang.activiti.demo.controller.form.Workflow;
import org.tang.activiti.demo.service.WorkflowService;

/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 工作流程控制器 </p>
 */
@Controller
public class WorkFlowController {

	@Autowired
	private WorkflowService workflowService;
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 部署列表视图 </p>
	 * @return
	 */
	@GetMapping("workflow_deploy_home")
	public ModelAndView deployHomeView(ModelAndView mv) {
		mv.addObject("workflow", new Workflow());
		mv.setViewName("views/workflow/workFlow.html");
		List<Deployment> depList = workflowService.listDeploy();
		List<ProcessDefinition> pdList = workflowService.listProcessDefinition();
		mv.addObject("depList", depList);
		mv.addObject("pdList", pdList);
		return mv;
	}
	
	@GetMapping("workflow_deploy_delete")
	public String deleteDeploy(Workflow workFlow) {
		workflowService.deleteProcessDefinition(workFlow.getDeploymentId());
		return "redirect:/workflow_deploy_home";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 任务列表视图 </p>
	 * @return
	 */
	@GetMapping("workflow_task_list")
	public String taskListView() {
		
		return "views/workflow/task.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 查看任务视图 </p>
	 */
	@GetMapping("workflow_task_form")
	public String taskFormView() {
		
		return "views/workflow/taskForm.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 查看当前流程运行图片 </p>
	 * @return
	 * @throws IOException 
	 */
	@GetMapping("workflow_viewImage")
	public void workflowViewImage(String processDefinitionId) throws IOException {
		InputStream	inputStream = workflowService.getProcessDefinitionInputStream(processDefinitionId);
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		ServletOutputStream outputStream = response.getOutputStream();
		StreamUtils.copy(inputStream, outputStream);
		// return "views/workflow/image.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 浏览历史审核记录 </p>
	 * @return
	 */
	@GetMapping("workflow_viewHisComment")
	public String hisCommentView() {
		
		return "views/workflow/taskFormHis.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 申请请假 </p>
	 * @return
	 */
	@GetMapping("workflow_start_process")
	public String workFlowStartProcess() {
		
		return "redirect:/leaveBill_list";
	}	
	
	/**
	 * 
	 * @date 2019年11月21日
	 * @desc <p> 上传文件 </p>
	 * @param workflow
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("workflow_upload")
	public String upload(Workflow workflow) throws IOException {
		workflowService.deployProcess(workflow.getImageName(),workflow.getFile().getInputStream());
		return "redirect:/workflow_deploy_home";
	}
	
}	
