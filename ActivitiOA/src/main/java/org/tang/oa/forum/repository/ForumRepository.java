package org.tang.oa.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.tang.oa.base.repository.BaseRepository;
import org.tang.oa.forum.domin.Forum;
/**
 * 
 * @date   2019年12月12日
 * @author tangxing
 * @desc   <p> 板块的数据层接口 </p>
 */
public interface ForumRepository extends BaseRepository<Forum, Long>{

	@Query(nativeQuery = true,value = "SELECT * FROM OA_FORUM WHERE POSITION < :position ORDER BY POSITION DESC LIMIT 0,1 ")
	Forum getPreForum(int position);

	@Query(nativeQuery = true, value = " SELECT * FROM OA_FORUM WHERE POSITION > :position ORDER BY POSITION ASC LIMIT 0,1  ")
	Forum getNextForum(int position);

}
