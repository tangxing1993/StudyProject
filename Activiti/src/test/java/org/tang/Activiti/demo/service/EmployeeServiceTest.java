package org.tang.Activiti.demo.service;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tang.activiti.demo.domain.Employee;
import org.tang.activiti.demo.service.EmployeeService;

import junit.framework.TestCase;
/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 雇员业务测试 </p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest extends TestCase {
	
	@Autowired
	EmployeeService employeeService;
	
	@Test
	public void testGetOneByName() {
		String name = "范冰冰";
		Optional<Employee> optional = employeeService.getOneByName(name);
		assertEquals(true, optional.isPresent());
	}
	
}
