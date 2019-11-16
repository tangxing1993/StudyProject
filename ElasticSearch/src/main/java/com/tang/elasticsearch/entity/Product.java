package com.tang.elasticsearch.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @date   2019年11月16日
 * @author tangxing
 * @desc   <p> 产品对象 </p>
 */
@Entity
@Table(name="products")
@Document(indexName = "product", type = "product")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="pid")
	private String id;
	
	@Field(store = true)
	private String pname;
	
	@GeoPointField
	private int catalog;
	
	@Field(store = true)
	private String catalogName;
	
	@Field(store = true,type = FieldType.Double)
	private double price;
	
	@Field(store = true,type = FieldType.Integer)
	private int number;
	
	@Field(store = true)
	private String description;
	
	@Field(store = true)
	private String picture;
	
	@Field(type = FieldType.Date,store = true,format =DateFormat.basic_date_time)
	private Date release_time;

}
