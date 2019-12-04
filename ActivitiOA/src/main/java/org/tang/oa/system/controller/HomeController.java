package org.tang.oa.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tang.oa.base.controller.BaseController;
import org.tang.oa.system.domin.User;

/**
 * 
 * @date   2019年12月4日
 * @author tangxing
 * @desc   <p> 布局和登录登出控制器 </p>
 */
@Controller
public class HomeController extends BaseController{

	/**
	 * 
	 * @date 2019年12月4日
	 * @desc <p> 跳转登录页面 </p>
	 * @return
	 */
	@RequestMapping("/loginUI")
	public String loginUI() {
		
		return "/login.html";
	}

	/**
	 * 
	 * @date 2019年12月4日
	 * @desc <p> 执行登录操作 </p>
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@NonNull String loginName,@NonNull String password,Model model,HttpServletRequest request) {
		User user =  userService.findByLoginNameAndPassword(loginName,password);
		if(user == null) {
			model.addAttribute("errorMsg", "用户名或者密码错误");
			return "forward:loginUI";
		}
		request.getSession().setAttribute("loginUser", user);
		return "redirect:/indexUI";
	}
	
	/**
	 * 
	 * @date 2019年12月4日
	 * @desc <p> 执行登出操作 </p>
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/logout.html";
	}
	
	@RequestMapping("/indexUI")
	public String indexUI() {
		
		return "/index.html";
	}
	
	@RequestMapping("/topUI")
	public String topUI() {
		return "/view/home/top.html";
	}
	
	@RequestMapping("/leftUI")
	public String leftUI(Model model) {
		// 获取顶级权限列表
		// List<Privilege> topPrivilegeList =	privilegeService.listForTopPrivilege();
		// 从全局作用域中获取数据  页面直接使用application作用域来调用
		// @SuppressWarnings("unchecked")
		// List<Privilege> topPrivilegeList = (List<Privilege>) request.getSession().getServletContext().getAttribute("topPrivilegeList");
		// model.addAttribute("topPrivilegeList", topPrivilegeList);
		return "/view/home/left.html";
	}
	
	@RequestMapping("/bottomUI")
	public String bottomUI() {
		return "/view/home/bottom.html";
	}
	
}
