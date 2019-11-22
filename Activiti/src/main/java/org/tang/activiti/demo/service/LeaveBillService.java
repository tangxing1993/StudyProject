package org.tang.activiti.demo.service;

import java.util.List;

import org.tang.activiti.demo.domain.LeaveBill;
import org.tang.activiti.demo.repository.LeaveBillRepository;
/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 请假单的业务接口 </p>
 */
public interface LeaveBillService extends BaseService<LeaveBillRepository, LeaveBill, Integer> {
	/**
	 * 
	 * @date 2019年11月22日
	 * @desc <p> 获取当前用户的请假单 </p>
	 * @return
	 */
	List<LeaveBill> listCurrentUserLeaveBill();

}
