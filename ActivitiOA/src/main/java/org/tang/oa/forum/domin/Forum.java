package org.tang.oa.forum.domin;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.tang.oa.base.domin.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 * @date   2019年12月12日
 * @author tangxing
 * @desc   <p> 板块实体 </p>
 */
@Table(name = "OA_FORUM")
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
public class Forum extends BaseEntity {

	private String name;
	
	private String description;
	
	private int position;
	
}
