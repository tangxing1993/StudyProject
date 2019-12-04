package org.tang.oa.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.tang.oa.base.service.impl.BaseServiceImpl;
import org.tang.oa.system.domin.User;
import org.tang.oa.system.repository.UserRepository;
import org.tang.oa.system.service.UserService;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 岗位的业务实现 </p>
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserRepository> implements UserService {

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		return dao.findByLoginNameAndPassword(loginName,DigestUtils.md5DigestAsHex(password.getBytes()));
	}



}
