package org.tang.activiti.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 工作流程控制器 </p>
 */
@Controller
public class WorkFlowController {

	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 部署列表视图 </p>
	 * @return
	 */
	@GetMapping("workflow_deploy_home")
	public String deployHomeView() {
		
		return "views/workflow/workFlow.html";
	}
	
	@GetMapping("workflow_deploy_delete")
	public String deleteDeploy() {
		
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
	 */
	@GetMapping("workflow_task_cur_image")
	public String taskCurImageView() {
		
		return "views/workflow/image.html";
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
}	
