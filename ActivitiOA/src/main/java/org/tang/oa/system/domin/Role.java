package org.tang.oa.system.domin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.tang.oa.base.domin.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 岗位实体 </p>
 */
@Entity
@Table(name = "OA_ROLE")
@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseEntity {

	private String name;
	
	private String description;
	
	@ManyToMany
    @JoinTable(
	        name="OA_USER_ROLE",
	        joinColumns=
	            @JoinColumn(name="ROLE_ID", referencedColumnName="ID"),
	        inverseJoinColumns=
	            @JoinColumn(name="USER_ID", referencedColumnName="ID")
	    )
	private Set<User> users = new HashSet<>();
	
	// 角色拥有的权限
	@ManyToMany
	@JoinTable(
			name = "OA_ROLE_PRIVILEGE",
			joinColumns = 
				@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"),
			inverseJoinColumns = 
				@JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID")
			)
	private Set<Privilege> privileges = new HashSet<>();
	
}
