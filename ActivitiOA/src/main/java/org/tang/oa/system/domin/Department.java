package org.tang.oa.system.domin;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.tang.oa.base.domin.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 * @date   2019年12月1日
 * @author tangxing
 * @desc   <p> 部门实体 </p>
 */
@Entity
@Table(name = "OA_DEPARTMENT")
@Data
@EqualsAndHashCode(callSuper=false)
public class Department extends BaseEntity{

	private String name;
	
	private String description;
	
	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "PARENT_ID",referencedColumnName = "ID")
	private Department parent;
	
	@OneToMany(targetEntity = Department.class,cascade = CascadeType.REMOVE)
	@JoinColumn(name = "PARENT_ID",referencedColumnName = "ID")
	@EqualsAndHashCode.Exclude
	private Set<Department> child;
	
	@OneToMany(targetEntity = User.class)
	@JoinColumn(name = "DEPARTMENT_ID",referencedColumnName = "ID")
	@EqualsAndHashCode.Exclude
	private Set<User> users;
	
}
