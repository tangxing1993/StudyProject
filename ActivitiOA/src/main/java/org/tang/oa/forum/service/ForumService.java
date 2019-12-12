package org.tang.oa.forum.service;

import org.tang.oa.base.service.BaseService;
import org.tang.oa.forum.domin.Forum;
/**
 * 
 * @date   2019年12月12日
 * @author tangxing
 * @desc   <p> 板块的业务层接口 </p>
 */
public interface ForumService extends BaseService<Forum>{
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 上移版块信息 </p>
	 * @param id
	 */
	void moveUp(Long id);
	/**
	 * 
	 * @date 2019年12月12日
	 * @desc <p> 下移版块信息 </p>
	 * @param id
	 */
	void moveDown(Long id);
}
