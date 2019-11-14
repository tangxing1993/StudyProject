package com.tang.solr.demo.solrj.test;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.params.SolrParams;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tang.solr.demo.solrj.dao.entity.Product;

/**
 * 
 * @date   2019年10月27日
 * @author tangxing
 * @desc   <p> SolrJ的测试类 </p>
 */
public class TestSolrJ {

	// 基本Url
	static final String BASE_URL = "http://192.168.1.18:8983/solr/core2";
	HttpSolrClient client;
	/**
	 * 
	 * @date 2019年10月27日
	 * @desc <p> 初始化连接客户端信息 </p>
	 */
	@Before
	public void init() {
		client = new HttpSolrClient.Builder(BASE_URL).build();
	}

	/**
	 * 
	 * @date 2019年10月27日
	 * @desc <p> 测试SolrJ添加索引 </p>
	 * @throws Exception
	 */
	@Test
	public void testAddDocument()throws Exception {
		// 创建一个Url连接
		Product product = new Product();
		product.setPId(10000);
		product.setPName("测试Solrj添加文档索引");
		product.setCatalogName("测试分类");
		product.setDescription("测试描述");
		product.setPrice(9.5);
		product.setPicture("aaa.jpg");
		client.addBean(product);
		// 提交事务
		client.commit();
		System.out.println("--- 添加索引成功 ---");
	}
	
	/**
	 * 
	 * @date 2019年10月27日
	 * @desc <p> 测试更新  如果ID存在则更新不存在则添加 </p>
	 * @throws Exception
	 */
	@Test
	public void testSaveUpdateDocument()throws Exception {
		// 创建一个Url连接
		Product product = new Product();
		product.setPId(10001);
		product.setPName("测试Solrj添加文档索引2");
		product.setCatalogName("测试分类");
		product.setDescription("测试描述");
		product.setPrice(9.5);
		product.setPicture("aaa.jpg");
		client.addBean(product);
		// 提交事务
		client.commit();
		System.out.println("--- 保存索引成功 ---");
	}
	
	/**
	 * 
	 * @date 2019年10月27日
	 * @desc <p> 测试删除索引 </p>
	 * @throws Exception
	 */
	@Test
	public void testDeleteDocument()throws Exception {
		// 通过id删除索引
		// UpdateResponse response = client.deleteById("10001");
		// 通过查询删除索引
		UpdateResponse response = client.deleteByQuery("*");
		int status = response.getStatus();	
		System.out.println(status);
		client.commit();
		System.out.println("--- 删除成功 ---");
	}
	
	/**
	 * 
	 * @date 2019年10月27日
	 * @desc <p> 测试简单查询 </p>
	 * @throws Exception
	 */
	@Test
	public void testSimpleQuery()throws Exception{
		SolrParams params = new SolrQuery("*");
		QueryResponse response = client.query(params);
		client.commit();
		System.out.println("--- 数据查询成功 ---");
		List<Product> products = response.getBeans(Product.class);
		System.out.println("数量: " + products.size());
		products.forEach(System.out::println);
	}
	
	/**
	 * 
	 * @date 2019年10月27日
	 * @desc <p> 测试复杂查询 </p>
	 * @throws Exception
	 */
	@Test
	public void testComplexQuery()throws Exception{
		SolrQuery query = new SolrQuery();
		// 封装查询条件
		 query.set("q", "prod_pname:宝宝");
		// 过滤查询
		query.setFilterQueries("prod_catalog_name:幽默杂货");
		// 查询显示的数据字段
		query.setFields("id","prod_pname","prod_price");
		// 排序
		query.addOrUpdateSort("prod_price", ORDER.asc);
		// 分页 类似MySQL的分页  start 偏移量  row 每页条数
		query.setStart(0);
		query.setRows(10);
		// 设置查询结果高亮
		query.setHighlight(true);
		query.addHighlightField("prod_pname");
		query.setHighlightSimplePre("<p style='color:red;'>");
		query.setHighlightSimplePost("</p>");
		QueryResponse response = client.query(query);
		client.commit();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		System.out.println(highlighting);
		long totalCount = response.getResults().getNumFound();
		System.out.println("查询到的结果总数为: " + totalCount);
		List<Product> products = response.getBeans(Product.class);
		System.out.println("结果数量:" + products.size());
		products.forEach(product -> {
			Map<String, List<String>> map = highlighting.get(product.getId());
			if(map != null) {
				System.out.println(map.get("prod_pname").get(0));
			}
		});
	}
	
	/**
	 * 
	 * @date 2019年10月27日
	 * @desc <p> 关闭连接 </p>
	 * @throws Exception
	 */
	@After
	public void close()throws Exception {
		client.close();
	}
	
}
