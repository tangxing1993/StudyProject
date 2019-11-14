package com.tang.solr.demo.solrj.dao.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @date   2019年10月28日
 * @author tangxing
 * @desc   <p> 查询条件 </p>
 */
@Data
public class Condition implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 搜索关键字
	private String keyword;
	
	// 过滤查询
	private String fq;
	
	// 价格区间
	private String pq; 
	
	// 排序
	private String pSort;

	// 当前页
	private int pageNum = 1;
	
	// 每页内容
	private int pageSize = 10;
	
	public static final String SORT_ASC = "1";
	
	public static final String SORT_DESC = "2";
	
}
