package org.tang.activiti.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tang.activiti.demo.domain.LeaveBill;
/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 请假单数据层接口  </p>
 */
public interface LeaveBillRepository extends JpaRepository<LeaveBill, Integer> {
	/**
	 * 
	 * @date 2019年11月22日
	 * @desc <p> 使用用户姓名获取请假单列表 </p>
	 * @param userName 用户姓名
	 * @return
	 */
	@Query(value = "FROM LeaveBill WHERE employee.name=:name")
	List<LeaveBill> listUserLeaveBill(@Param("name") String userName);

}
