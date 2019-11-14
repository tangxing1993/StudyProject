package com.tang.elasticsearch.repository;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.tang.elasticsearch.entity.Content;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @date   2019年11月13日
 * @author tangxing
 * @desc   <p> 测试 Elasticsearch Repository </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ContentRepositoryTest {

	@Autowired
	ContentRepository contentRepository;
	@Autowired
	ElasticsearchTemplate template;
	
	/**
	 * 
	 * @date 2019年11月13日
	 * @desc <p> 测试创建索引 </p>
	 */
	@Test
	public void testCreateIndex() {
		assertEquals(true, template.createIndex(Content.class));
	}
	
	/**
	 * @date 2019年11月13日
	 * @desc <p> 测试添加数据 </p>
	 */
	@Test
	public void testSave() {
		Content content = new Content("1", "鲁智深", "倒拔垂杨柳", "施耐庵", 1);
		contentRepository.save(content);
	}
	
	/**
	 * 
	 * @date 2019年11月13日
	 * @desc <p> 删除索引信息 </p>
	 */
	@Test
	public void testDeleteIndex() {
		boolean index = template.deleteIndex(Content.class);
		assertEquals(true, index);
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 根据Id查询索引 </p>
	 */
	@Test
	public void testFindById() {
		Optional<Content> optional = contentRepository.findById("1");
		log.info("content: {} ", optional.get());
		assertEquals(true, "鲁智深".equals(optional.get().getName()));
	}

	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 判断是否存在该id </p>
	 */
	@Test
	public void testExistsById() {
		boolean exists = contentRepository.existsById("100");
		assertEquals(false, exists);
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 查询所有索引数据 </p>
	 */
	@Test
	public void testFindAll() {
		Iterable<Content> iterable = contentRepository.findAll();
		Iterator<Content> iterator = iterable.iterator();
		iterator.forEachRemaining(System.out::println);
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 测试通过id列表来查询数据 </p>
	 */
	@Test
	public void testFindByIds() {
		Iterable<Content> iterable = contentRepository.findAllById(Lists.list("2","3"));
		iterable.iterator().forEachRemaining(System.out::println);
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 测试索引总数 </p>
	 */
	@Test
	public void testCount() {
		long count = contentRepository.count();
		assertEquals(3, count);
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 通过Id删除索引 </p>
	 */
	@Test
	public void testDeleteById() {
		contentRepository.deleteById("3");
		assertEquals(2, contentRepository.count());
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 测试通过对象实例删除索引 </p>
	 */
	@Test
	public void testDeleteByEntity() {
		Optional<Content> contentOp = contentRepository.findById("2");
		contentOp.ifPresent(content -> {
			contentRepository.delete(content);
		});
		try {
			contentOp.orElseThrow(Exception::new);
		} catch (Exception e) {
			e.printStackTrace();
			fail("删除失败");
		}
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 测试通过QueryBuilder搜索 </p>
	 */
	@Test
	public void testSearchByTermQueryBuilder() {
		TermQueryBuilder query = new TermQueryBuilder("id", "1");
		Iterable<Content> search = contentRepository.search(query);
		search.iterator().forEachRemaining(System.out::println);
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 使用bool查询搜索 </p>
	 */
	@Test
	public void testSearchByBoolQueryBuilder() {
		BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
		// 构建bool查询条件
		RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("sort");
		rangeQueryBuilder.from(2, true);
		boolQueryBuilder.must(rangeQueryBuilder);
		// 设置返回的字段
		boolQueryBuilder.queryName("name");
		Iterable<Content> search = contentRepository.search(boolQueryBuilder);
		search.iterator().forEachRemaining(System.out::println);
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 测试结果高亮 </p>
	 */
	@Test
	public void testHighlight() {
		MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "林");
		NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(matchQuery).withHighlightFields(new HighlightBuilder.Field("name")).build();
		template.queryForPage(query, Content.class, new SearchResultMapper() {
			@Override
			public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				SearchHits hits = response.getHits();
				if(hits.totalHits == 0) {
					return null;
				}
				hits.forEach(searchHit -> {
					System.out.println(searchHit.getHighlightFields().get("name").fragments()[0].toString());
				});
				return null;
			}

			@Override
			public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
	}
	
	/**
	 * 
	 * @date 2019年11月14日
	 * @desc <p> 批量创建索引  </p>
	 */
	@Test
	public void testSaveAll() {
		ArrayList<Content> list = (ArrayList<Content>) Lists.list(new Content("2", "林冲", "十万禁军教头", "施耐庵", 2),
								  new Content("3", "扈三娘", "三打祝家庄", "施耐庵", 3)
				  );
	    contentRepository.saveAll(list);
	}

}
