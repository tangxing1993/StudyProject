package org.tang.activiti.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.tang.activiti.demo.domain.Employee;
import org.tang.activiti.demo.domain.LeaveBill;
import org.tang.activiti.demo.service.EmployeeService;
import org.tang.activiti.demo.service.LeaveBillService;
import org.tang.activiti.demo.util.SessionContext;

/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 请假单控制器 </p>
 */
@Controller
public class LeaveBillController {

	@Autowired
	private LeaveBillService leaveBillService;
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 请假单列表 </p>
	 * @return
	 */
	@GetMapping("leaveBill_list")
	public ModelAndView listView(ModelAndView mv) {
		mv.setViewName("views/leaveBill/list.html");
		List<LeaveBill> leaveBills = leaveBillService.listCurrentUserLeaveBill();
		mv.addObject("leaveBills", leaveBills);
		return mv;
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 添加请假单申请 </p>
	 * @return
	 */
	@GetMapping("leaveBill_input")
	public ModelAndView inputView(Integer id,ModelAndView mv) {
		LeaveBill leaveBill = null;
		if(id == null) {
			leaveBill = new LeaveBill();
		}else {
			leaveBill = leaveBillService.findById(id).get();
		}
		mv.addObject("leaveBill", leaveBill);
		mv.setViewName("views/leaveBill/input.html");
		return mv;
	}
	
	/**
	 * 
	 * @date 2019年11月22日
	 * @desc <p> 保存请假单信息 </p>
	 * @return
	 */
	@PostMapping("leaveBill_save")
	public String leaveBillSave(LeaveBill leaveBill) {
		if(leaveBill.getId() == null) {
			Optional<Employee> optional = employeeService.getOneByName(SessionContext.getLoginUserName());
			leaveBill.setEmployee(optional.get());
		}
		leaveBillService.save(leaveBill);
		return "redirect:/leaveBill_list";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 删除请假单 </p>
	 * @return
	 */
	@GetMapping("leaveBill_delete")
	public String delete(LeaveBill leaveBill) {
		leaveBillService.delete(leaveBill);
		return "redirect:/leaveBill_list";
	}
	
}
