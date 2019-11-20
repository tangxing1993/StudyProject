package org.tang.activiti.demo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.tang.activiti.demo.domain.Employee;
import org.tang.activiti.demo.repository.EmployeeRepository;
import org.tang.activiti.demo.service.EmployeeService;
/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 雇员的业务实现 </p>
 */
@Service
public class EmployeeServiceImpl extends AbstractService<EmployeeRepository, Employee, Integer> implements EmployeeService {

	@Override
	public Optional<Employee> getOneByName(String name) {
		Employee employee = this.dao.getOneByName(name);
		return Optional.of(employee) ;
	}

	

}
