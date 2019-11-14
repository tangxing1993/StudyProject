package com.tang.solr.demo.solrj.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import com.tang.solr.demo.solrj.dao.entity.Condition;
import com.tang.solr.demo.solrj.dao.entity.Product;
import com.tang.solr.demo.solrj.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProductServiceImpl {

	@Autowired
	ProductService productService;
	
	@Test
	public void testSearchBycondition() throws Exception {
		Condition condition = new Condition();
		Page<Product> searchBycondition = productService.searchBycondition(condition);
		System.out.println(searchBycondition.getPageable() + "\t" + searchBycondition.getTotalPages() + "\t" + searchBycondition.getTotalElements());
		searchBycondition.get().forEach(System.out::println);
	}

}
