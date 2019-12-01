package org.tang.oa.system.domin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.tang.oa.base.domin.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 用户实体 </p>
 */
@Entity
@Table(name = "OA_USER")
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseEntity {
	
	private String loginName;
	
	private String password;
	
	private String name;
	
	private String gender;
	
	private String phoneNumber;
	
	private String email;
	
	private String description;

	@ManyToOne(targetEntity = Department.class)
	@JoinColumn(name = "DEPARTMENT_ID",referencedColumnName = "ID")
	private Department department;
	
	@ManyToMany
    @JoinTable(
	        name="OA_USER_ROLE",
	        joinColumns=
	            @JoinColumn(name="USER_ID", referencedColumnName="ID"),
	        inverseJoinColumns=
	            @JoinColumn(name="ROLE_ID", referencedColumnName="ID")
	    )
	@EqualsAndHashCode.Exclude
	private Set<Role> roles = new HashSet<>();
}
