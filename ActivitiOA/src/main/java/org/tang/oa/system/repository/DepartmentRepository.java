package org.tang.oa.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.tang.oa.base.repository.BaseRepository;
import org.tang.oa.system.domin.Department;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 岗位的数据层接口 </p>
 */
public interface DepartmentRepository extends BaseRepository<Department, Long> {

	/**
	 * 
	 * @date 2019年12月1日
	 * @desc <p> 获取顶级部门的列表信息 </p>
	 * @return
	 */
	@Query("FROM Department d where d.parent is null ")
	List<Department> listOfTop();
	
	/**
	 * 
	 * @date 2019年12月1日
	 * @desc <p> 获取子部门的列表信息 </p>
	 * @param parentId 父部门标识
	 * @return
	 */
	@Query("FROM Department d where d.parent.id=:parentId")
	List<Department> listOfChildren(Long parentId);


}
