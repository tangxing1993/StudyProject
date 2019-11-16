package com.tang.elasticsearch.servcie.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.elasticsearch.entity.Product;
import com.tang.elasticsearch.repository.es.ProductRepository;
import com.tang.elasticsearch.repository.jpa.ProductDao;
import com.tang.elasticsearch.servcie.ProductService;

/**
 * 
 * @date   2019年11月16日
 * @author tangxing
 * @desc   <p> 产品的业务实现 </p>
 */
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productDao;
	@Autowired
	ProductRepository esDao;
	
	@Override
	public List<Product> list() {
		return productDao.findAll();
	}

	@Override
	public void save(Product product) {
		productDao.save(product);
	}

	@Override
	public void indexAll(List<Product> products) {
		esDao.saveAll(products);
	}

}
