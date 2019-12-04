package org.tang.oa.system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.tang.oa.base.repository.BaseRepository;
import org.tang.oa.system.domin.Privilege;
/**
 * 
 * @date   2019年12月2日
 * @author tangxing
 * @desc   <p> 权限的数据接口 </p>
 */
public interface PrivilegeRepository extends BaseRepository<Privilege, Long> {

	/**
	 * 
	 * @date 2019年12月4日
	 * @desc <p> 获取顶级权限列表 </p>
	 * @return
	 */
	@Query("FROM Privilege WHERE parent IS NULL ")
	List<Privilege> listForTopPrivilege();

}
