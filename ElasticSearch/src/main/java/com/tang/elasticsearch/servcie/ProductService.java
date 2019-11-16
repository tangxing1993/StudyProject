package com.tang.elasticsearch.servcie;

import java.util.List;

import com.tang.elasticsearch.entity.Product;
/**
 * 
 * @date   2019年11月16日
 * @author tangxing
 * @desc   <p> 产品的业务接口 </p>
 */
public interface ProductService {

	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> </p>
	 * @return
	 */
	List<Product> list();
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 保存 </p>
	 * @param product
	 */
	void save(Product product);
	
	/**
	 * 
	 * @date 2019年11月16日
	 * @desc <p> 索引产品列表 </p>
	 * @param products
	 */
	void indexAll(List<Product> products);
}
