package org.tang.oa.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tang.oa.base.service.impl.BaseServiceImpl;
import org.tang.oa.system.domin.Department;
import org.tang.oa.system.repository.DepartmentRepository;
import org.tang.oa.system.service.DepartmentService;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 部门的业务实现 </p>
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department, DepartmentRepository> implements DepartmentService {

	@Override
	public List<Department> listOfTop() {
		return dao.listOfTop();
	}

	@Override
	public List<Department> listOfChildren(Long parentId) {
		return dao.listOfChildren(parentId);
	}


}
