package org.tang.oa.base.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 通用的业务接口 </p>
 */
public interface BaseService<T> {
	/**
	 * 
	 * @date 2019年11月30日
	 * @desc <p> 保存或更新实体 </p>
	 * @param entity
	 */
	void save(T entity);
	/**
	 * 
	 * @date 2019年11月30日
	 * @desc <p> 批量保存实体 </p>
	 * @param <S>
	 * @param entities
	 */
	<S extends T> void saveAll(Iterable<S> entities);
	/**
	 * 
	 * @date 2019年11月30日
	 * @desc <p> 根据id删除实体 </p>
	 * @param id
	 */
	void deleteById(Long id);
	/**
	 * 
	 * @date 2019年11月30日
	 * @desc <p> 根据实体对象删除实体 </p>
	 * @param entity
	 */
	void delete(T entity);
	/**
	 * 
	 * @date 2019年11月30日
	 * @desc <p> 根据id获取实体对象 </p>
	 * @param roleId
	 * @return
	 */
	T findById(Long roleId);
	/**
	 * 
	 * @date 2019年12月1日
	 * @desc <p> 根据id获取实体对象 </p>
	 * @param ids
	 * @return
	 */
	List<T> findAllById(Collection<Long> ids);
	/**
	 * 
	 * @date 2019年11月30日
	 * @desc <p> 查询所有的实体 </p>
	 * @return
	 */
	List<T> findAll();
	/**
	 * 
	 * @date 2019年11月30日
	 * @desc <p> 获取分页数据对象 </p>
	 * @param pageable
	 * @return
	 */
	Page<T> findAll(Pageable pageable);
}
