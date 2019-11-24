package org.tang.activiti.demo.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @date   2019年11月19日
 * @author tangxing
 * @desc   <p> 雇员 </p>
 */
@Table(name = "t_employee")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class Employee extends AbstractPersistable<Integer>{

	@NotNull(message = "雇员名称不能为空")
	private String name;
	
	@NotNull(message = "密码不能为空")
	@Length(min = 6,message = "密码长度最少6位")
	private String password;
	
	@Email(message = "邮箱不符合规则")
	private String email;
	
	private String role;
	
	@Override
	public void setId(Integer id) {
		super.setId(id);
	}

	// 多对一  使用JoinColumn来指定外键   外键存储在多的一方
	@ManyToOne(targetEntity = Employee.class)
	@JoinColumn(name = "manager_id")
	private Employee manager;
	
}
