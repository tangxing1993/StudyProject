package org.tang.oa.system.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tang.oa.base.controller.BaseController;
import org.tang.oa.system.domin.Department;
import org.tang.oa.system.domin.Role;
import org.tang.oa.system.domin.User;
import org.tang.oa.util.DepartmentUtils;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 用户的控制器 </p>
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController {

	
	@RequestMapping("list")
	public String list(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		return "/view/system/user/list.html";
	}

	@RequestMapping("addUI")
	public String addUI(Model model) {
		// 准备部门数据
		List<Department> departments = null;
		departments = departmentService.listOfTop();
		departments = DepartmentUtils.getAllDepartmentList(departments);
		model.addAttribute("departments", departments);
		// 准备岗位数据
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
		model.addAttribute("departmentId", 0);
		model.addAttribute("user", new User());
		model.addAttribute("genders", Arrays.asList("男","女"));
		return "/view/system/user/saveUI.html";
	}

	@RequestMapping("add")
	public String add(User user,Long departmentId, Long[] roleIdList) {
		Department department = departmentService.findById(departmentId);
		user.setDepartment(department);
		List<Role> roles = roleService.findAllById(Arrays.asList(roleIdList));
		user.setRoles(new HashSet<>(roles));
		user.setPassword(DigestUtils.md5DigestAsHex("1234".getBytes()));
		userService.save(user);
		return "redirect:list";
	}

	@RequestMapping("editUI/{id}")
	public String editUI(@PathVariable("id") Long userId,Model model) {
		
		// 准备部门数据
		List<Department> departments = null;
		departments = departmentService.listOfTop();
		departments = DepartmentUtils.getAllDepartmentList(departments);
		model.addAttribute("departments", departments);
		// 准备岗位数据
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		
		// 准备用户数据
		User user = userService.findById(userId);
		model.addAttribute("user", user);
		
		model.addAttribute("genders", Arrays.asList("男","女"));
		
		if(user.getDepartment() != null) {
			model.addAttribute("departmentId", user.getDepartment().getId());
		}
		

		
		return "/view/system/user/saveUI.html";
	}

	@RequestMapping("edit")
	public String edit(User user,Long departmentId, Long[] roleIdList) {
		User existUser = userService.findById(user.getId());
		BeanUtils.copyProperties(user, existUser,"password");
		Department department = departmentService.findById(departmentId);
		existUser.setDepartment(department);
		List<Role> roles = roleService.findAllById(Arrays.asList(roleIdList));
		existUser.setRoles(new HashSet<>(roles));
		userService.save(existUser);
		return "redirect:list";
	}

	@RequestMapping("delete")
	public String delete(@RequestParam("id") Long userId) {
		User existUser = userService.findById(userId);
		userService.delete(existUser);
		return "redirect:list";
	}
	
	@RequestMapping("initPassword")
	public String initPassword(User user) {
		User existUser = userService.findById(user.getId());
		existUser.setPassword(DigestUtils.md5DigestAsHex("1234".getBytes()));
		userService.save(existUser);
		return "redirect:list";
	}

	
}
