package org.tang.oa.system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tang.oa.base.service.impl.BaseServiceImpl;
import org.tang.oa.system.domin.Privilege;
import org.tang.oa.system.repository.PrivilegeRepository;
import org.tang.oa.system.service.PrivilegeService;
/**
 * 
 * @date   2019年12月2日
 * @author tangxing
 * @desc   <p> 权限的业务层实现 </p>
 */
@Service
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege, PrivilegeRepository> implements PrivilegeService {

	@Override
	public List<Privilege> listForTopPrivilege() {
		return dao.listForTopPrivilege();
	}

}
