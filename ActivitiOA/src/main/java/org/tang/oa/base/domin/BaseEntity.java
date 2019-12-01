package org.tang.oa.base.domin;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 通用的实体类 </p>
 */
@MappedSuperclass
public abstract class BaseEntity extends AbstractPersistable<Long>{

	public void setId(@Nullable Long id) {
		super.setId(id);
	}
	
}
