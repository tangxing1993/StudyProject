package org.tang.activiti.demo.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p>业务层接口</p>
 */
public interface BaseService<DAO extends JpaRepository<T, ID>,T,ID> {

	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 保存对象 </p>
	 * @param <S>
	 * @param entity
	 * @return
	 */
	<S extends T> S save(S entity);
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 是否存在该Id的实体 </p>
	 * @param id
	 * @return
	 */
	boolean existsById(ID id);
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 根据ID查找实例 </p>
	 * @param id
	 * @return
	 */
	Optional<T> findById(ID id);

	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 查询所有列表 </p>
	 * @return
	 */
	Iterable<T> findAll();
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 根据Id查找一个 </p>
	 * @param id
	 * @return
	 */
	T getOne(ID id);

	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 数据总数 </p>
	 * @return
	 */
	long count();
	
	/**
	 * 
	 * @date 2019年11月20日
	 * @desc <p> 由对象实体删除数据 </p>
	 * @param entity
	 */
	void delete(T entity);
	
}
