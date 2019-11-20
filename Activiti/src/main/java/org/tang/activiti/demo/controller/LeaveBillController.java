package org.tang.activiti.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 请假单控制器 </p>
 */
@Controller
public class LeaveBillController {

	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 请假单列表 </p>
	 * @return
	 */
	@GetMapping("leaveBill_list")
	public String listView() {
		
		return "views/leaveBill/list.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 添加请假单申请 </p>
	 * @return
	 */
	@GetMapping("leaveBill_input")
	public String inputView() {
		
		return "views/leaveBill/input.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 删除请假单 </p>
	 * @return
	 */
	@GetMapping("leaveBill_delete")
	public String delete() {
		
		return "redirect:/leaveBill_list";
	}
	
}
