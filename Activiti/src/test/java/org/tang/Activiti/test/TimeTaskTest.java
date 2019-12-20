package org.tang.Activiti.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * 
 * <p>2019年12月20日</p>
 * @author tangxing
 * <p> 测试边界任务 </p>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TimeTaskTest {

	@Autowired
	ProcessEngine processEngine;
	
	// 部署流程信息
	@Test
	public void testDeploy() {
		Deployment deploy = processEngine.getRepositoryService().createDeployment()
					 .name("边界任务")
					 .addClasspathResource("processes/TimeJob.bpmn")
					 .addClasspathResource("processes/TimeJob.png")
					 .deploy();
		System.out.println(deploy.toString());
		
	}
	
	// 启动流程实例
	@Test
	public void testStartProcessInstance() {
		String processDefinitionKey = "TimeJob";
		Map<String, Object> variables = new HashMap<String, Object>();
		// 设置初始变量
		variables.put("timeout", LocalDateTime.now().plusSeconds(15l).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
		ProcessInstance processInstance = processEngine.getRuntimeService()
					 .startProcessInstanceByKey(processDefinitionKey, variables);
		System.out.println(processInstance.toString());
		try {
			Thread.sleep(2*60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
