package org.tang.oa.system.service;

import java.util.List;

import org.tang.oa.base.service.BaseService;
import org.tang.oa.system.domin.Privilege;
/**
 * 
 * @date   2019年12月2日
 * @author tangxing
 * @desc   <p> 权限的业务层接口 </p>
 */
public interface PrivilegeService extends BaseService<Privilege> {
	
	/**
	 * 
	 * @date 2019年12月4日
	 * @desc <p> 获取顶级权限列表 </p>
	 * @return
	 */
	List<Privilege> listForTopPrivilege();

}
