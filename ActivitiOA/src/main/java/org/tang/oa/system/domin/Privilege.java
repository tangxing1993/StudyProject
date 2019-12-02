package org.tang.oa.system.domin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.tang.oa.base.domin.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @date   2019年12月2日
 * @author tangxing
 * @desc   <p> 权限实体 </p>
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "OA_PRIVILEGE")
public class Privilege extends BaseEntity{

	// 名称
	private String name;
	
	// 路径
	private String url;
	
	// 权限角色
	@ManyToMany
	@JoinTable( name = "OA_ROLE_PRIVILEGE",
				joinColumns = @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID"),
				inverseJoinColumns = @JoinColumn( name = "ROLE_ID", referencedColumnName = "ID" )
			)
	@EqualsAndHashCode.Exclude
	private Set<Role> roles = new HashSet<>();
	
	// 父权限
	@ManyToOne
	@JoinColumn( name = "PARENT_ID" ,referencedColumnName = "ID")
	private Privilege parent;
	
	// 子权限
	@OneToMany
	@EqualsAndHashCode.Exclude
	@JoinColumn( name = "PARENT_ID" ,referencedColumnName = "ID")
	private Set<Privilege> children = new HashSet<>();

}
