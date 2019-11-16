package com.tang.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.tang.elasticsearch.entity.Content;
/**
 * 
 * @date   2019年11月13日
 * @author tangxing
 * @desc   <p> 文章的es接口 </p>
 */
public interface ContentRepository extends ElasticsearchRepository<Content, String> {

}
