package org.tang.oa.system.service;

import org.tang.oa.base.service.BaseService;
import org.tang.oa.system.domin.User;

/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p>岗位的业务接口</p>
 */
public interface UserService extends BaseService<User>{
	
	/**
	 * 
	 * @date 2019年12月4日
	 * @desc <p> 根据登录名和密码查询用户 </p>
	 * @param loginName
	 * @param password
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
