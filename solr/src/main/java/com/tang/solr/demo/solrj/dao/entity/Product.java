package com.tang.solr.demo.solrj.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;

import lombok.Data;
/**
 * 
 * @date   2019年10月27日
 * @author tangxing
 * @desc   <p> 产品实体类 </p>
 */
@Data
@Table(name = "products")
@Entity
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pid")
	private int pId;
	
	@Field(value = "id")
	private String id;
	
	@Field(value ="prod_pname")
	private String pName;
	
	private int catalog;
	
	@Column(name="catalog_name")
	@Field(value ="prod_catalog_name")
	private String catalogName;
	
	@Field("prod_price")
	private double price;
	
	private int number;
	
	@Field("prod_description")
	private String description;
	
	@Field("prod_picture")
	private String picture;
	
	@Column(name="release_time")
	private Date releaseTime;
	
	
	
	
}
