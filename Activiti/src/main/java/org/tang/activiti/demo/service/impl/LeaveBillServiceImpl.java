package org.tang.activiti.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.tang.activiti.demo.domain.LeaveBill;
import org.tang.activiti.demo.repository.LeaveBillRepository;
import org.tang.activiti.demo.service.LeaveBillService;
import org.tang.activiti.demo.util.SessionContext;
/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 请假单的业务实现 </p>
 */
@Service
public class LeaveBillServiceImpl extends AbstractService<LeaveBillRepository, LeaveBill, Integer> implements LeaveBillService {

	@Override
	public List<LeaveBill> listCurrentUserLeaveBill() {
		String name = SessionContext.getLoginUserName();
		return dao.listUserLeaveBill(name);
	}

	
}
