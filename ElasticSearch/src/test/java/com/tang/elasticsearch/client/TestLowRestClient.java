package com.tang.elasticsearch.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClient.FailureListener;
import org.elasticsearch.client.RestClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @date   2019年11月7日
 * @author tangxing
 * @desc   <p> 测试ES的低级客户端 </p>
 */
public class TestLowRestClient {

	public RestClient client;
	
	@Before
	public void init() {
		// 使用RestClient构造器构建RestClient
		HttpHost host_1 = new HttpHost("192.168.1.18", 9200, "http");
		HttpHost host_2 = new HttpHost("192.168.1.18", 9201, "http");
		HttpHost host_3 = new HttpHost("192.168.1.18", 9202, "http");
		RestClientBuilder builder = RestClient.builder(host_1,host_2,host_3);
		// 失败执行器
		FailureListener failureListener = new FailureListener() {
			@Override
			public void onFailure(Node node) {
				System.out.println(String.format("%s Build Error!", node.getName()));
			}
		};
		builder.setFailureListener(failureListener);
		client = builder.build();
	}
	
	/**
	 * 
	 * @date 2019年11月7日
	 * @desc <p> 判断集群状态 </p>
	 */
	@Test
	public void testClusterState() {
		Request request = new Request("GET", "/_cluster/state");
		try {
			Response response = client.performRequest(request);
			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @date 2019年11月7日
	 * @desc <p> 搜索所有的数据 </p>
	 */
	@Test
	public void testSearch() {
		Request request = new Request("POST", "/_all/_search");
		try {
			Response response = client.performRequest(request);
			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
		} catch (IOException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	@After
	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
