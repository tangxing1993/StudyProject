package org.tang.activiti.demo.service;

import java.util.Optional;

import org.springframework.lang.NonNull;
import org.tang.activiti.demo.domain.Employee;
import org.tang.activiti.demo.repository.EmployeeRepository;

/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 雇员服务接口 </p>
 */
public interface EmployeeService extends BaseService<EmployeeRepository, Employee, Integer>{
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 使用雇员姓名获取雇员信息 </p>
	 * @param name
	 * @return
	 */
	Optional<Employee> getOneByName(@NonNull String name);
	
}
