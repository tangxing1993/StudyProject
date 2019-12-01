package org.tang.oa.system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tang.oa.base.controller.BaseController;
import org.tang.oa.system.domin.Department;
import org.tang.oa.util.DepartmentUtils;

/**
 * 
 * @date 2019年11月30日
 * @author tangxing
 * @desc
 *       <p>
 *       	部门的控制器
 *       </p>
 */
@Controller
@RequestMapping("/department/")
public class DepartmentController extends BaseController {
	
	@RequestMapping("list")
	public String list(Model model,@RequestParam(value="id",required = false) Long parentId) {
		List<Department> department = null;
		/**
		 * 显示所有的部门信息
		 */
		// department = departmentService.findAll();
		
		/**
		 * 如果parentId存在显示子部门列表，不存在显示父部门列表
		 */
		if(parentId == null) {
			department = departmentService.listOfTop();
		}else {
			department = departmentService.listOfChildren(parentId);
		}
		model.addAttribute("departments", department);
		model.addAttribute("parentId", parentId);
		// 获取父部门的父部门编号
		Department parent = departmentService.findById(parentId);
		if(parent!=null && parent.getParent()!=null) {
			model.addAttribute("grandFatherId", parent.getParent().getId());
		}
		return "/view/system/department/list.html";
	}

	@RequestMapping("addUI")
	public String addUI(Model model,@RequestParam(value="id",required = false) Long parentId) {
		// 获取部门列表回显数据
		List<Department> departments = null;
		// department = departmentService.findAll();
		departments = departmentService.listOfTop();
		departments = DepartmentUtils.getAllDepartmentList(departments);
		
		model.addAttribute("departments", departments);
		model.addAttribute("parentId", parentId);
		model.addAttribute("department", new Department());
		return "/view/system/department/saveUI.html";
	}

	@RequestMapping("add")
	public String add(Department department,Long parentId) {
		department.setParent(departmentService.findById(parentId));
		departmentService.save(department);
		String url = "redirect:list";
		return parentId != null && parentId != 0 ? url + "?id=" + parentId : url;
	}

	@RequestMapping("editUI/{id}")
	public String editUI(@PathVariable("id") Long departmentId,Model model) {
		List<Department> departments = null;
		// departments = departmentService.findAll();
		departments = departmentService.listOfTop();
		departments = DepartmentUtils.getAllDepartmentList(departments);
		model.addAttribute("departments", departments);
		Department department = departmentService.findById(departmentId);
		if(department != null && department.getParent()!=null ) {
			model.addAttribute("parentId", department.getParent().getId());
		}
		model.addAttribute("department", department);
		return "/view/system/department/saveUI.html";
	}

	@RequestMapping("edit")
	public String edit(Department department,Long parentId) {
		Department parentDepartment = departmentService.findById(parentId);
		Department existDepartment = departmentService.findById(department.getId());
		existDepartment.setName(department.getName());
		existDepartment.setDescription(department.getDescription());
		existDepartment.setParent(parentDepartment);
		departmentService.save(existDepartment);
		String url = "redirect:list";
		return parentId != null && parentId != 0 ? url + "?id=" + parentId : url;
	}

	@RequestMapping("delete")
	public String delete(@RequestParam("id") Long departmentId) {
		Department existDepartment = departmentService.findById(departmentId);
		String url = "redirect:list";
		if(existDepartment.getParent()!=null) {
			url += "?id=" + existDepartment.getParent().getId();
		}
		departmentService.delete(existDepartment);
		return url;
	}

}
