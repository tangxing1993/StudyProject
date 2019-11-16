package com.tang.elasticsearch.repository;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tang.elasticsearch.ElasticSearchApplication;
import com.tang.elasticsearch.servcie.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchApplication.class)
class ProductServcieTest {

	@Autowired
	ProductService productService;
	
	@Test
	void testList() {
		productService.list().forEach(System.out::println);;
	}

	@Test
	void testSave() {
		fail("Not yet implemented");
	}

	@Test
	void testIndexAll() {
		productService.indexAll(productService.list());
	}

}
