package org.tang.oa.system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tang.oa.base.controller.BaseController;
import org.tang.oa.system.domin.Role;

/**
 * 
 * @date 2019年11月30日
 * @author tangxing
 * @desc
 *       <p>
 *       岗位的控制器
 *       </p>
 */
@Controller
@RequestMapping("/role/")
public class RoleController extends BaseController {
	
	
	@RequestMapping("list")
	public String list(Model model) {
		List<Role> roles = roleService.findAll();
		model.addAttribute("roles", roles);
		return "/view/system/role/list.html";
	}

	@RequestMapping("addUI")
	public String addUI(Model model) {
		model.addAttribute("role", new Role());
		return "/view/system/role/saveUI.html";
	}

	@RequestMapping("add")
	public String add(Role role) {
		roleService.save(role);
		return "redirect:list";
	}

	@RequestMapping("editUI/{id}")
	public String editUI(@PathVariable("id") Long roleId,Model model) {
		Role role = roleService.findById(roleId);
		model.addAttribute("role", role);
		return "/view/system/role/saveUI.html";
	}

	@RequestMapping("edit")
	public String edit(Role role) {
		Role existRole = roleService.findById(role.getId());
		existRole.setName(role.getName());
		existRole.setDescription(role.getDescription());
		roleService.save(existRole);
		return "redirect:list";
	}

	@RequestMapping("delete")
	public String delete(@RequestParam("id") Long roleId) {
		Role existRole = roleService.findById(roleId);
		roleService.delete(existRole);
		return "redirect:list";
	}

}
