package com.tang.solr.demo.solrj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tang.solr.demo.solrj.dao.entity.Condition;
import com.tang.solr.demo.solrj.dao.entity.Product;
import com.tang.solr.demo.solrj.service.ProductService;

/**
 * 
 * @date   2019年10月28日
 * @author tangxing
 * @desc   <p> 产品的页面控制器 </p>
 */
@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;

	@GetMapping("search")
	public ModelAndView search(ModelAndView mv,
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "") String fq) {
		mv.setViewName("index");
		Condition condition = new Condition();
		condition.setKeyword(keyword);
		condition.setPageNum(pageNum);
		condition.setFq(fq);
		try {
			Page<Product> pages = productService.searchBycondition(condition);
			List<String> categorys = productService.getAllCategory();
			mv.addObject("pages", pages);
			mv.addObject("keyword", keyword);
			mv.addObject("categorys", categorys);
			mv.addObject("fq", fq);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
}
