package org.tang.oa.forum.service.impl;

import org.springframework.stereotype.Service;
import org.tang.oa.base.service.impl.BaseServiceImpl;
import org.tang.oa.forum.domin.Forum;
import org.tang.oa.forum.repository.ForumRepository;
import org.tang.oa.forum.service.ForumService;
/**
 * 
 * @date   2019年12月12日
 * @author tangxing
 * @desc   <p> 板块的业务实现 </p>
 */
@Service
public class ForumServiceImpl extends BaseServiceImpl<Forum, ForumRepository> implements ForumService {
	
	@Override
	public void save(Forum entity) {
		if(entity.getId() == null) {
			super.save(entity);
			entity.setPosition(entity.getId().intValue());
		}else {
			super.save(entity);
		}
	}

	/**
	 *  -  交换上下的版块位置
	 */
	@Override
	public void moveUp(Long id) {
		Forum forum = dao.findById(id).get();
		Forum preForum = dao.getPreForum(forum.getPosition());
		if(preForum == null) {
			return;
		}
		int tmpPosition = forum.getPosition();
		forum.setPosition(preForum.getPosition());
		preForum.setPosition(tmpPosition);
	}

	@Override
	public void moveDown(Long id) {
		Forum forum = dao.findById(id).get();
		Forum nextForum = dao.getNextForum(forum.getPosition());
		if(nextForum == null) {
			return;
		}
		int tmpPosition = forum.getPosition();
		forum.setPosition(nextForum.getPosition());
		nextForum.setPosition(tmpPosition);
	}

}
