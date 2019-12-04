package org.tang.oa.system.domin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
	        name="OA_USER_ROLE",
	        joinColumns=
	            @JoinColumn(name="USER_ID", referencedColumnName="ID"),
	        inverseJoinColumns=
	            @JoinColumn(name="ROLE_ID", referencedColumnName="ID")
	    )
	@EqualsAndHashCode.Exclude
	private Set<Role> roles = new HashSet<>();
	
	public boolean isPrivilege(String privilegeName) {
		// 判断是否是超级管理员用户
		if(isAdmin()) {
			return true;
		}
		// 判断普通用户是否具有当前权限
		for(Role role : roles) {
			for( Privilege privilege : role.getPrivileges() ) {
				if(privilege.getName().equals(privilegeName)) {
					return true;
				}
			}
		}
		
		return Boolean.FALSE;
	}
	
	/**
	 * 
	 * @date 2019年12月5日
	 * @desc <p> 判断是否是超级管理员 </p>
	 * @return
	 */
	private boolean isAdmin() {
		return "admin".equals(loginName);
	}
	
}
