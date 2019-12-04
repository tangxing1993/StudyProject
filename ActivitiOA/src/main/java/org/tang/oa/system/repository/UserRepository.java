package org.tang.oa.system.repository;

import org.springframework.data.jpa.repository.Query;
import org.tang.oa.base.repository.BaseRepository;
import org.tang.oa.system.domin.User;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 用户的数据层接口 </p>
 */
public interface UserRepository extends BaseRepository<User, Long> {
	
	/**
	 * 
	 * @date 2019年12月4日
	 * @desc <p> 根据用户名和密码查询用户 </p>
	 * @param loginName 登录名
	 * @param password  密码
	 * @return
	 */
	@Query("FROM User WHERE loginName=:loginName and password=:password ")
	User findByLoginNameAndPassword(String loginName, String password);

}
