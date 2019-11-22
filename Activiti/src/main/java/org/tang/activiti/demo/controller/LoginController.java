package org.tang.activiti.demo.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tang.activiti.demo.domain.Employee;
import org.tang.activiti.demo.service.EmployeeService;
import org.tang.activiti.demo.util.SessionContext;

/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 登录控制器 </p>
 */
@Controller
public class LoginController {


	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 登录页面 </p>
	 * @return
	 */
	@GetMapping("/login")
	public String loginView() {
		return "login.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 登录认证操作 </p>
	 * @return
	 */
	@PostMapping("/login")
	public String login(@Valid @NotNull(message = "用户名不能为空") @RequestParam String name) {
		Optional<Employee> optional = employeeService.getOneByName(name);
		optional.ifPresent(employee -> {
			SessionContext.saveLoginUser(employee.getName());
		});
		return optional.isPresent() ? "redirect:/index" : "redirect:/login";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 跳转首页 </p>
	 * @return
	 */
	@RequestMapping("/index")
	public String indexView() {
		// TODO Render Index Page
		
		return "views/main.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 顶部视图 </p>
	 * @return
	 */
	@RequestMapping("/top")
	public String topView() {
	
		return "views/top.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 左侧菜单视图 </p>
	 * @return
	 */
	@RequestMapping("/left")
	public String leftView() {
		
		return "views/left.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 欢迎界面视图 </p>
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcomeView() {
		
		return "views/welcome.html";
	}
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 登出 </p>
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		SessionContext.invalidate();
		return "redirect:/login";
	}
}
