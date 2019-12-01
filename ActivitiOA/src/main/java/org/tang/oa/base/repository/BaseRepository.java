package org.tang.oa.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 数据层接口 </p>
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

}
