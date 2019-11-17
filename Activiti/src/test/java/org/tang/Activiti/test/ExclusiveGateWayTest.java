package org.tang.Activiti.test;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.assertj.core.util.Maps;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date   2019年11月17日
 * @author tangxing
 * @desc   <p> 排它网关测试 </p>
 */
@Slf4j
public class ExclusiveGateWayTest  extends AbstractProcessTest{

	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 测试money=200 </p>
	 */
	@Test
	public void testExclusiveGateWay() {
		Deployment deploy = this.processEngine.getRepositoryService()
						  .createDeployment()
						  .addClasspathResource("processes/ExclusiveGateWay.bpmn")
						  .addClasspathResource("processes/ExclusiveGateWay.png")
						  .name("排它网关")
						  .deploy();
		this.deployPrint(deploy);
		ProcessInstance processInstance = this.processEngine.getRuntimeService()
						  .startProcessInstanceByKey("exclusiveGateWay", Maps.newHashMap("money", 200));
		this.processInstancePrint(processInstance);
		log.info("执行成功");
	}
	
	@Test
	public void testTaskCommit() {
		this.processEngine.getTaskService().complete("32511");
		log.info("任务完成");
	}
	
	
}
