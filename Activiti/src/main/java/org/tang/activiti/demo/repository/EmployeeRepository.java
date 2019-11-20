package org.tang.activiti.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tang.activiti.demo.domain.Employee;
/**
 * 
 * @date   2019年11月19日
 * @author tangxing
 * @desc   <p> 雇员的数据层接口 </p>
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> </p>
	 * @param name
	 * @return
	 */
	Employee getOneByName(String name);
}
