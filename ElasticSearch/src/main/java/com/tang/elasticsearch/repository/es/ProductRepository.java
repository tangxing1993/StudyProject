package com.tang.elasticsearch.repository.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tang.elasticsearch.entity.Product;
/**
 * 
 * @date   2019年11月16日
 * @author tangxing
 * @desc   <p> 产品的ES数据层接口 </p>
 */
public interface ProductRepository extends ElasticsearchRepository<Product, String>{

}
