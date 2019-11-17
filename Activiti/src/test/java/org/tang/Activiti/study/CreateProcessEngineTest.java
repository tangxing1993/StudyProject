package org.tang.Activiti.study;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * 
 * @date   2019年11月16日
 * @author tangxing
 * @desc   <p> 引擎创建测试 </p>
 */
public class CreateProcessEngineTest {

	@Test
	public void testCreate() {
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		// 设置参数
		configuration.setJdbcUrl("jdbc:mysql://192.168.1.18:3306/process_engine?useSSl=false");
		configuration.setJdbcDriver("com.mysql.jdbc.Driver");
		configuration.setJdbcUsername("root");
		configuration.setJdbcPassword("123456");
		// 设置数据表创建
		configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		// 构建工作流引擎
		ProcessEngine processEngine = configuration.buildProcessEngine();
		String name = processEngine.getName();
		System.out.println("ProcessEngineName:" + name);
	}
	
}
