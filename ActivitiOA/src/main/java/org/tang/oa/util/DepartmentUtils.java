package org.tang.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.tang.oa.system.domin.Department;

/**
 * 
 * @date   2019年12月1日
 * @author tangxing
 * @desc   <p> 部门的工具类 </p>
 */
public abstract class DepartmentUtils {

	public static List<Department> getAllDepartmentList(List<Department> departments) {
		List<Department> result = new ArrayList<Department>();
		walkTree(departments,"┢",result);
		return result;
	}

	/**
	 * 
	 * @date 2019年12月1日
	 * @desc <p> 遍历部门列表并给部门名称加上前缀 </p>
	 * @param departments
	 * @param prefix
	 * @param result
	 */
	public static void walkTree(Collection<Department> departments,String prefix,Collection<Department> result) {
		for(Department department : departments) {
			Department copyDepartment = new Department();
			copyDepartment.setId(department.getId());
			copyDepartment.setName( prefix + department.getName() );
			result.add(copyDepartment);
			walkTree(department.getChild(), "　" + prefix, result);
		}
		
	}
	
}
