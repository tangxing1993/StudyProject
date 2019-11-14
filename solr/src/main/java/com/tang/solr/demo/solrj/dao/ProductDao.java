package com.tang.solr.demo.solrj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tang.solr.demo.solrj.dao.entity.Product;
/**
 * 
 * @date   2019年10月27日
 * @author tangxing
 * @desc   <p> 产品的数据层接口 </p>
 */
public interface ProductDao extends JpaRepository<Product, Integer>{

	/**
	 * 
	 * @date 2019年10月29日
	 * @desc <p> 查询所有的分类列表 </p>
	 * @return 分类列表数据
	 */
	@Query("SELECT DISTINCT catalogName FROM Product")
	List<String> getAllCategory();
	
}
