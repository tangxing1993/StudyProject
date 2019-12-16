package org.tang.oa.forum.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tang.oa.base.controller.BaseController;
import org.tang.oa.forum.domin.Forum;
/**
 * 
 * @date   2019年12月12日
 * @author tangxing
 * @desc   <p> 板块的控制器  </p>
 */
@Controller
@RequestMapping("/forum/")
public class ForumController extends BaseController {

	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 板块列表 </p>
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model) {
		Page<Forum> page = forumService.findAll(PageRequest.of(0, 1000, Sort.by("position").ascending()));
		model.addAttribute("forumList", page.getContent());
		return "/view/forum/list.html";
	}
	
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 创建板块页面 </p>
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		model.addAttribute("forum", new Forum());
		return "/view/forum/saveUI.html";
	}
	
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 添加板块 </p>
	 * @return
	 */
	@RequestMapping("add")
	public String add(Forum forum) {
		forumService.save(forum);
		return "redirect:list";
	}
	
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 编辑板块页面 </p>
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Long id,Model model) {
		Forum forum = forumService.findById(id);
		model.addAttribute("forum", forum);
		return "/view/forum/saveUI.html";
	}
	
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 编辑板块 </p>
	 * @return
	 */
	@RequestMapping("edit")
	public String edit(Forum forum) {
		forumService.save(forum);
		return "redirect:list";
	}
	
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 删除板块 </p>
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Long id) {
		forumService.deleteById(id);
		return "redirect:list";
	}
	
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 上移板块 </p>
	 * @return
	 */
	@RequestMapping("moveUp")
	public String moveUp(Long id) {
		forumService.moveUp(id);
		return "redirect:list";
	}
	
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 下移板块 </p>
	 * @return
	 */
	@RequestMapping("moveDown")
	public String moveDown(Long id) {
		forumService.moveDown(id);
		return "redirect:list";
	}
	
}
