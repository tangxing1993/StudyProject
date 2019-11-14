package com.tang.lucene.demo.basic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
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
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * @date 2019年10月26日
 * @author tangxing
 * @desc
 *       <p>
 *       索引的测试程序
 *       </p>
 */
public class TestLuceneIndex {

	static String tmpDir = FileUtils.getTempDirectoryPath();

	// 创建索引
	@Test
	public void testIndexWrite() throws Exception {
		// 创建索引目录位置
		Directory directory = FSDirectory.open(new File(tmpDir, "lucene/index").toPath());
		// 自定义一个中文分词器
		IKAnalyzer ikAnalyzer = new IKAnalyzer(true);
		// 获取IndexWriter对象
		try (IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(ikAnalyzer))) {
			// 读取磁盘上的文件
			File toIndexDir = new File(
					"C:\\Users\\17611\\Documents\\workspace\\study\\lucene\\src\\main\\resources\\toIndexDir");
			Arrays.asList(toIndexDir.listFiles()).parallelStream().forEach(file -> {
				// 获取文件名称
				String name = file.getName();
				// 获取文件路径
				String path = file.getPath();
				// 获取文件内容
				String content = "";
				try {
					content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 获取文件大小
				long fileSize = FileUtils.sizeOf(file);
				// 创建域信息
				Field nameField = new TextField("name", name, Store.YES);
				Field pathField = new StoredField("path", path);
				Field contentField = new TextField("content", content, Store.YES);
				Field fileSizeField = new LongPoint("fileSize", fileSize);
				Field fileSizeStore = new StoredField("fileSize", fileSize);
				// 创建文档对象
				Document document = new Document();
				document.add(nameField);
				document.add(pathField);
				document.add(contentField);
				document.add(fileSizeField);
				document.add(fileSizeStore);
				// 写入文档
				try {
					indexWriter.addDocument(document);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			System.out.println("索引创建成功!");
		}
	}

	/**
	 * @date 2019年10月26日
	 * @desc
	 *       <p>
	 *       执行索引查询
	 *       </p>
	 * @throws Exception
	 */
	@Test
	public void testIndexSearch() throws Exception {
		// 创建索引目录对象
		Directory directory = FSDirectory.open(new File(tmpDir, "lucene/index").toPath());
		// 创建索引读对象
		try (IndexReader indexReader = DirectoryReader.open(directory);) {
			// 创建索引查询对象
			IndexSearcher indexSearch = new IndexSearcher(indexReader);
			// 创建查询执行器
			Query query = new TermQuery(new Term("content", "构"));
			// 执行查询获取TopDocs
			TopDocs topDocs = indexSearch.search(query, 10);
			// 获取查询到的文档个数
			System.out.println("总数:" + topDocs.totalHits);
			// 遍历ScoreDoc
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			Arrays.asList(scoreDocs).parallelStream().forEach(scoreDoc -> {
				// 获取文档id
				int docId = scoreDoc.doc;
				// 查询文档对象
				try {
					Document document = indexSearch.doc(docId);
					System.out.println("name: " + document.get("name"));
					System.out.println("path: " + document.get("path"));
					System.out.println("fileSize: " + document.get("fileSize"));
					// System.out.println("content: " + document.get("content"));
					System.out.println("-------------------------------------");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}

	}

	// 查看分词器的分词效果
	@Test
	public void testTokenStream() throws Exception {
		try (Analyzer analyzer = new StandardAnalyzer();) {
			TokenStream tokenStream = analyzer.tokenStream("", "我是一个中国人！");
			CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				System.out.println(charTermAttribute.toString());
			}
		}
	}
	
	/**
	 * @date 2019年10月26日
	 * @desc <p> 测试IKAnalyzer分词器的分词效果 </p>
	 * @throws Exception
	 */
	@Test
	public void testIKAnalyzer()throws Exception {
		try(IKAnalyzer anlyzer = new IKAnalyzer(true);){
			TokenStream tokenStream = anlyzer.tokenStream("", "我是一个中国人!");
			CharTermAttribute addAttribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while(tokenStream.incrementToken()) {
				System.out.println(addAttribute.toString());
			}
		}
	}
	
	/**
	 * 
	 * @date 2019年10月30日
	 * @desc <p> 测试分词器的包装类 </p>
	 */
	@Test
	public void testAnalyzerWrapper()throws Exception {
		// 初始化默认的分词器
		Map<String,Analyzer> analyzerPerField = new HashMap<>();
		// firstname字段使用标准分词器
		analyzerPerField.put("firstname", new StandardAnalyzer());
		PerFieldAnalyzerWrapper analyzer = new PerFieldAnalyzerWrapper(new IKAnalyzer(),analyzerPerField);
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		try(IndexWriter writer = new IndexWriter(FSDirectory.open(new File(tmpDir, "lucene/index").toPath()), conf);){
			Document doc  = new Document();
			doc.add(new StringField("id", "1", Store.YES));
			doc.add(new TextField("firstname", "123df/456qweds/asdasfasfa23/",Store.YES));
			doc.add(new TextField("content", "我是一个中国人",Store.YES));
			writer.addDocument(doc);
		}
	}
	
}
