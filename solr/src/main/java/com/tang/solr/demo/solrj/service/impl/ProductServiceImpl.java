package com.tang.solr.demo.solrj.service.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.CommonParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tang.solr.demo.solrj.dao.ProductDao;
import com.tang.solr.demo.solrj.dao.entity.Condition;
import com.tang.solr.demo.solrj.dao.entity.Product;
import com.tang.solr.demo.solrj.service.ProductService;
/**
 * 
 * @date   2019年10月28日
 * @author tangxing
 * @desc   <p> 产品服务的实现 </p>
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	SolrClient solrClient;
	@Autowired
	ProductDao productDao;
	
	@Override
	public Page<Product> searchBycondition(Condition condition) throws Exception {
		SolrQuery query = new SolrQuery();
		// 封装查询条件
		if(StringUtils.isEmpty(condition.getKeyword())) {
			query.setQuery("*");
		}else {
			query.setQuery(condition.getKeyword());
		}
		query.add(CommonParams.DF, "prod_pname");
		if(!StringUtils.isEmpty(condition.getFq())) {
			query.addFilterQuery("prod_catalog_name:" + condition.getFq());
		}
		if(!StringUtils.isEmpty(condition.getPq())) {
			String[] pqs = condition.getPq().split(Pattern.quote("-"));
			if(pqs.length == 1) {
				query.addFilterQuery("prod_price:[" + pqs[0] + " TO *]");
			}else {
				String prefix = StringUtils.isEmpty(pqs[0]) ? "*" : pqs[0];
				query.addFacetField("prod_price:[" + prefix + " TO " + pqs[1] + "]");
			}
		}
		if(!StringUtils.isEmpty(condition.getPSort())) {
			if(Condition.SORT_ASC.equals(condition.getPSort())) {
				query.addSort("prod_price", ORDER.asc);
			}else {
				query.addSort("prod_price", ORDER.desc);
			}
		}
		query.setStart((condition.getPageNum()-1)*condition.getPageSize());
		query.setRows(condition.getPageSize());
		query.setHighlight(true);
		query.setHighlightSimplePre("<span style='color:red'>");
		query.setHighlightSimplePost("</span>");
		QueryResponse response = solrClient.query(query);
		solrClient.commit();
		long total = response.getResults().getNumFound();
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		List<Product> products = response.getBeans(Product.class);
		products.forEach(product -> {
			Map<String, List<String>> map = highlighting.get(product.getId());
			if( map != null && map.get("prod_pname")!=null) {
				product.setPName(map.get("prod_pname").get(0));
			}
		});
		return new PageImpl<>(products, PageRequest.of(condition.getPageNum()-1, condition.getPageSize()) , total);
	}

	@Override
	public List<String> getAllCategory() {
		return productDao.getAllCategory();
	}

}
