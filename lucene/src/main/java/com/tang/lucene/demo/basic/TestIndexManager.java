package com.tang.lucene.demo.basic;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * @date   2019年10月26日
 * @author tangxing
 * @desc   <p> 索引库的维护案例 </p>
 */
public class TestIndexManager {

	static final String tmpDir = System.getProperty("java.io.tmpdir");
	
	IndexWriter indexWriter;
	IndexReader reader;
	IndexSearcher indexSearcher;
	
	/**
	 * @date 2019年10月26日
	 * @desc <p> 初始化加载数据  </p>
	 * @throws Exception
	 */
	@Before
	public void before() throws Exception {
		indexWriter = new IndexWriter(FSDirectory.open(new File(tmpDir, "lucene/index").toPath()),
				new IndexWriterConfig(new IKAnalyzer(true))); 
		reader = DirectoryReader.open(FSDirectory.open(new File(tmpDir, "lucene/index").toPath()));
		indexSearcher = new IndexSearcher(reader);
	}
	
	/**
	 * @date 2019年10月26日
	 * @desc <p> 销毁数据 </p>
	 * @throws Exception
	 */
	@After
	public void after()throws Exception {
		indexWriter.close();
		reader.close();
	}
	
	/**
	 * @date 2019年10月26日
	 * @desc <p> 创建索引 </p>
	 * @throws Exception
	 */
	@Test
	public void addIndex() throws Exception{
			Document document = new Document();
			document.add(new StringField("name", "这是一个文档的标题",Store.YES));
			document.add(new TextField("content", "我是内容中国人,哈哈", Store.YES));
			document.add(new StoredField("path", "c:/tmp/...."));
			document.add(new LongPoint("size", 123456l));
			indexWriter.addDocument(document);
			System.out.println("创建索引成功");
	}
	
	/**
	 * @date 2019年10月26日
	 * @desc <p> 更新索引 </p>
	 * @throws Exception
	 */
	@Test
	public void updateIndex() throws Exception{
		// 原理  先删除后添加
		Document doc = new Document();
		doc.add(new TextField("name", "更新之后的文档标题",Store.YES));
		doc.add(new TextField("content", "更新之后的文档的内容",Store.YES));
		doc.add(new StoredField("summary", "更新之后的文档的摘要"));
		long size = indexWriter.updateDocument(new Term("content", "哈哈哈"), doc);
		System.out.println("更新索引成功:" + size);
	}
	
	/**
	 * 
	 * @date 2019年10月26日
	 * @desc <p> 删除所有索引 </p>
	 * @throws Exception
	 */
	@Test
	public void deleteAllIndex()throws Exception {
		indexWriter.deleteAll();
		System.out.println("删除索引成功!");
	}
	
	/**
	 * @date 2019年10月26日
	 * @desc <p> 根据查询删除索引 </p>
	 * @throws Exception
	 */
	@Test
	public void deleteIndexByQuery() throws Exception{
		long size = indexWriter.deleteDocuments(new TermQuery(new Term("content", "哈哈")));
		System.out.println("查询删除成功:" + size);
	}
	
	/**
	 * 
	 * @date 2019年10月26日
	 * @desc <p> 数值查询 </p>
	 * @throws Exception
	 */
	@Test
	public void testRangeQuery()throws Exception {
		Query query = LongPoint.newRangeQuery("size", 0, 1000000000l);
		printResult(query);
	}
	
	/**
	 * 
	 * @date 2019年10月26日
	 * @desc <p> 查询执行结果 </p>
	 * @param query
	 * @throws IOException
	 */
	private void printResult(Query query) throws IOException {
		TopDocs search = indexSearcher.search(query, 10);
		System.out.println("总数量:" + search.totalHits);
		for(ScoreDoc doc : search.scoreDocs) {
			int docId = doc.doc;
			Document document = indexSearcher.doc(docId);
			System.out.println(document.toString());
		}
	}
	
	/**
	 * 
	 * @date 2019年10月26日
	 * @desc <p> 使用QueryParser执行查询 </p>
	 * @throws Exception
	 */
	@Test
	public void testQueryParser()throws Exception{
		QueryParser queryParser = new QueryParser("content", new IKAnalyzer(true));
		Query query = queryParser.parse("医疗卫生");
		printResult(query);
	}
	
	
}
