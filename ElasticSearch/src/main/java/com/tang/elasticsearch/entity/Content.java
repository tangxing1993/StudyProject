package com.tang.elasticsearch.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @date   2019年11月13日
 * @author tangxing
 * @desc   <p> 文章对象 </p>
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(type = "content",indexName = "cms",replicas = 1,shards = 2)
public class Content implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Field(store = true)
	private String id;
	@Field(store = true)
	private String name;
	@Field(store = true)
	private String content;
	@Field(store = true)
	private String author;
	@Field(store = true)
	private long sort;
	
}
