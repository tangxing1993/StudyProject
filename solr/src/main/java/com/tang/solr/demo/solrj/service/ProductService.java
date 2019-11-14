package com.tang.solr.demo.solrj.service;
/**
 * 
 * @date   2019年10月27日
 * @author tangxing
 * @desc   <p> 产品的业务层接口 </p>
 */

import java.util.List;

import org.springframework.data.domain.Page;

import com.tang.solr.demo.solrj.dao.entity.Condition;
import com.tang.solr.demo.solrj.dao.entity.Product;

public interface ProductService {
	
	/**
	 * 
	 * @date 2019年10月28日
	 * @desc <p> 根据条件搜索查询结果 </p>
	 * @param condition 产品的查询条件
	 * @return 查询的结果数据
	 * @throws Exception 
	 */
	Page<Product> searchBycondition(Condition condition) throws Exception;
	
	/**
	 * 
	 * @date 2019年10月29日
	 * @desc <p> 获取所有分类信息 </p>
	 * @return
	 */
	List<String> getAllCategory();
}
