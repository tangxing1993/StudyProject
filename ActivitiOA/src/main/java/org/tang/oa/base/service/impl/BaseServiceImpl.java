package org.tang.oa.base.service.impl;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.tang.oa.base.repository.BaseRepository;
import org.tang.oa.base.service.BaseService;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 抽象的业务实现 </p>
 */
@Transactional
public abstract class BaseServiceImpl<T, Dao extends BaseRepository<T, Long>> implements BaseService<T> {
	
	@Autowired
	protected Dao dao;

	@Override
	public void save(T entity) {
		dao.save(entity);
	}

	@Override
	public <S extends T> void saveAll(Iterable<S> entities) {
		dao.saveAll(entities);
	}

	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}

	@Override
	public void delete(T entity) {
		dao.delete(entity);
	}

	@Override
	public T findById(Long id) {
		if(id == null) {
			return null;
		}
		return dao.findById(id).orElse(null);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public List<T> findAllById(Collection<Long> ids) {
		return dao.findAllById(ids);
	}
	
	
	
}
