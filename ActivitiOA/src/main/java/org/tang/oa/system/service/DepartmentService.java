package org.tang.oa.system.service;

import java.util.List;

import org.tang.oa.base.service.BaseService;
import org.tang.oa.system.domin.Department;

/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p>岗位的业务接口</p>
 */
public interface DepartmentService extends BaseService<Department>{
	
	/**
	 * 
	 * @date 2019年12月1日
	 * @desc <p> 获取顶级部门的列表信息 </p>
	 * @return
	 */
	List<Department> listOfTop();
	
	/**
	 * 
	 * @date 2019年12月1日
	 * @desc <p> 获取子部门的列表信息 </p>
	 * @param parentId 父部门标识
	 * @return
	 */
	List<Department> listOfChildren(Long parentId);

}
