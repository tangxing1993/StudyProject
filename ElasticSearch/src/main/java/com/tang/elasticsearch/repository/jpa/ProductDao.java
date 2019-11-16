package com.tang.elasticsearch.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tang.elasticsearch.entity.Product;

/**
 * 
 * @date   2019年11月16日
 * @author tangxing
 * @desc   <p> 产品的业务接口 </p>
 */
public interface ProductDao extends JpaRepository<Product, String>{

}
