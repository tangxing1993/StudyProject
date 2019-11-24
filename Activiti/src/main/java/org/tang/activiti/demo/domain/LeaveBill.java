package org.tang.activiti.demo.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Table(name = "t_leavebill")
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class LeaveBill extends AbstractPersistable<Integer> {

	@NotNull(message = "请假天数不能为空")
	private Integer days;
	
	@NotNull(message = "请假内容不能为空")
	private String content;
	
	@Column(name = "leave_date")
	private Date leaveDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@NotNull(message = "雇员不能为空")
	private Employee employee;
	
	// 备注
	private String remark;
	
	// 请假状态
	private Integer state = STATE_NEW;
	
	// 录入
	public static final Integer STATE_NEW = 0;
	// 审批
	public static final Integer STATE_AUDIT = 1;
	// 审批完成
	public static final Integer STATE_COMPLETE = 2;
}
