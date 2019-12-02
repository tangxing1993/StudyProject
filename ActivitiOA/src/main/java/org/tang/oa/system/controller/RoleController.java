package org.tang.oa.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tang.oa.base.controller.BaseController;
import org.tang.oa.system.domin.Privilege;
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

	/**
	 * 
	 * @date 2019年12月2日
	 * @desc <p> 设置权限页面 </p>
	 * @return
	 */
	@RequestMapping("editPrivilegeUI")
	public String editPrivilegeUI(@NonNull Long roleId, Model model) {
		// 权限列表数据
		List<Privilege> privileges = privilegeService.findAll();
		model.addAttribute("roleId", roleId);
		model.addAttribute("privileges", privileges);
		Role role = roleService.findById(roleId);
		// 当前角色对应的权限
		Set<Privilege> curRolePrivileges = role.getPrivileges();
		if(curRolePrivileges != null ) {
			List<Long> privilegeIds = new ArrayList<>(curRolePrivileges.size());
			for(Privilege privilege : curRolePrivileges) {
				privilegeIds.add(privilege.getId());
			}
			model.addAttribute("privilegeIds", privilegeIds);
		}
		return "/view/system/role/setPrivilegeUI.html";
	}
	
	/**
	 * 
	 * @date 2019年12月2日
	 * @desc <p> 保存权限 </p>
	 * @return
	 */
	@RequestMapping("savePrivilege")
	public String savePrivilege(Long roleId,Long[] privilegeIds) {
		Role curRole = roleService.findById(roleId);
		List<Privilege> privileges = privilegeService.findAllById(Arrays.asList(privilegeIds));
		curRole.setPrivileges(new HashSet<>(privileges));
		roleService.save(curRole);
		return "redirect:list";
	}
	
	
}
