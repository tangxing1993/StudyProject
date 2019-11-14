package com.tang.solr.demo.solrj.dao;
/**
 * 
 * @date   2019年10月27日
 * @author tangxing
 * @desc   <p> 测试JPA业务层接口 </p>
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestProductDao {

	@Autowired
	ProductDao productDao;
	
	@Test
	public void testCount() {
		assertEquals(3803l, productDao.count());
	}
	
}
