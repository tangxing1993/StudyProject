package org.tang.Activiti.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.junit.Test;
import org.springframework.util.StreamUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @date   2019年11月17日
 * @author tangxing
 * @desc   <p> 流程定义和部署测试CRUD </p>
 */
@Slf4j
public class ProcessDefineTest extends AbstractProcessTest{

	/**
	 * 
	 * @date 2019年11月17日
	 * @desc 
	 * <p> 
	   *  流程部署步骤:
	 * 	  1. 绘制流程图
	 * 	  2. 部署流程   【相同的流程定义key多次部署版本会自定升级】
	 *    3. 查看流程定义和部署  
	 * </p>
	 */
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 使用zip流部署流程 :   
	 * 			  流程部署信息对应数据库中	  ACT_RE_DEPLOYMENT表 
	 * 			  流程部署定义信息对应数据库中	  ACT_RE_PROCDEF表 
	 * 			  流程部署信息文件对应数据库中      ACT_GE_BYTEARRAY表 
	 * 		  </p>
	 */
	@Test
	public void testDeployByZipInputStream() {
		InputStream zipInputStream = this.getClass().getClassLoader().getResourceAsStream("processes/leaveprocess.zip");
		Deployment deploy = processEngine.getRepositoryService()
					 .createDeployment()
					 .addZipInputStream(new ZipInputStream(zipInputStream))
					 .name("请假单")
					 .deploy();
		deployPrint(deploy);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 测试类路径下部署流程 </p>
	 */
	@Test
	public void testDeployByClasspath() {
		Deployment deploy = processEngine.getRepositoryService()
					 .createDeployment()
					 .addClasspathResource("processes/leaveprocess.bpmn")
					 .addClasspathResource("processes/leaveprocess.png")
					 .name("类路径部署请假单")
					 .deploy();
		deployPrint(deploy);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 输入流部署请假单 </p>
	 */
	@Test
	public void testDeployByInputStream() {
		InputStream inputStreamBpm = this.getClass().getClassLoader().getResourceAsStream("processes/leaveprocess.bpmn");
		InputStream inputStreamPng = this.getClass().getClassLoader().getResourceAsStream("processes/leaveprocess.png");
		Deployment deploy = processEngine.getRepositoryService()
					 .createDeployment()
					 .addInputStream("leaveProcess.bpmn", inputStreamBpm)
					 .addInputStream("leaveProcess.png", inputStreamPng)
					 .name("输入流部署流程")
					 .deploy();	   
		deployPrint(deploy);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 流程部署查询 </p>
	 */
	@Test
	public void testDeployQuery() {
		String deploymentId = "17501";
		Deployment deploy = processEngine.getRepositoryService()
					 .createDeploymentQuery()  // 获取部署查询器
				  // .deploymentCategory(category) //部署分类查询
			      // .deploymentCategoryLike(categoryLike)  // 部署分类模糊查询
					 .deploymentId(deploymentId)  // 部署Id查询
				  // .deploymentKey(key)		// 部署key查询
				  // .deploymentKeyLike(keyLike) // 部署key模糊查询
				  // .deploymentName(name)       // 部署名字查询
				  // .deploymentNameLike(nameLike)  // 部署名字模糊查询
				  // .count()				// 查询结果数量
				  // .asc()				// 查询结果升序排列
				  // .desc()			// 查询结果降序排列
				  // .listPage(firstResult, maxResults)    // 查询结果分页   首页偏移量，每页数据条数
				  // .list()            // 结果列表 
				  // .orderByDeploymentId()  // 按照部署id排序
					 .singleResult();
		deployPrint(deploy);
	}
	
	/**
	 * 
	 * @date 2019年11月17日
	 * @desc <p> 流程定义查询:  对应数据库表 【ACT_RE_PROCDEF】 
	 * 			 ProcessDefinitionQuery   			// 支持java对象查询 不支持复杂查询
	 * 			 NavtiveProcessDefinitionQuery		// 支持SQL查询和复杂查询，仅支持少量的对象查询
	 *       </p>
	 */
	@Test
	public void testProcessDefineQuery() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService()
					 .createProcessDefinitionQuery()   // 创建对象查询器
					 //.deploymentId(deploymentId)     // 部署Id查询
					 //.deploymentIds(deploymentIds)   // 部署Id列表查询
					 //.processDefinitionCategory(processDefinitionCategory)  // 流程定义分类查询
					 //.processDefinitionCategoryLike(processDefinitionCategoryLike)  // 流程定义分类模糊查询
					 .processDefinitionKey("leaveprocess")  // 流程定义key查询
					 //.processDefinitionKeyLike(processDefinitionKeyLike) // 流程定义key模糊查询
					 //.processDefinitionName(processDefinitionName) // 流程定义名字查询
					 //.processDefinitionNameLike(processDefinitionNameLike) // 流程定义名字模糊查询
					 //.count()  // 查询结果数量
					 .latestVersion()  // 最新版本
					 //.list()  // 查询列表
					 //.listPage(firstResult, maxResults) // 分页查询
					 //.orderByDeploymentId().asc()       // 按照部署Id升序排列
					 //.orderByProcessDefinitionKey().desc() // 按照流程定义key降序排列
					 .singleResult();
		log.info("流程定义Id: {} ", processDefinition.getId());
		log.info("流程定义Key: {} ", processDefinition.getKey());
		log.info("流程定义Name: {} ", processDefinition.getName());
		log.info("流程定义DeploymentId: {} ", processDefinition.getDeploymentId());
		log.info("流程定义Category: {} ", processDefinition.getCategory());
		log.info("流程定义Description: {} ", processDefinition.getDescription());
		log.info("流程定义EngineVersion: {} ", processDefinition.getEngineVersion());
		log.info("流程定义Version: {} ", processDefinition.getVersion());
		log.info("流程定义DiagramResourceName: {} ", processDefinition.getDiagramResourceName());
	}
	
	/**
	 * 
	 * @throws IOException 
	 * @date 2019年11月17日
	 * @desc <p> 流程定义资源查询: 对应数据库【ACT_GE_BYTEARRAY】 </p>
	 */
	@Test
	public void testProcessDefinitionResource() {
		ProcessDefinition processDefinition = processEngine.getRepositoryService()
											 .createProcessDefinitionQuery() 
											 .processDefinitionKey("leaveprocess")
											 .latestVersion()
											 .singleResult();
		InputStream processDiagram = processEngine.getRepositoryService()
					 .getProcessDiagram(processDefinition.getId());
		try {
			log.info("bytes: {} ", StreamUtils.copyToByteArray(processDiagram).toString());
		} catch (IOException e) {
			e.printStackTrace();
			fail(" 获取图资源失败 ");
		}
	}
	
	
}
