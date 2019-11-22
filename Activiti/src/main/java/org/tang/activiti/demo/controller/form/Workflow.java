package org.tang.activiti.demo.controller.form;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 流程对象 </p>
 */
@Data
public class Workflow {

	// 部署文件
	private MultipartFile file;
	
	// 申请单id
	private Long id;
	
	// 部署对象id
	private String deploymentId;
	
	// 资源名称
	private String imageName;
	
	// 任务id
	private String taskId;
	
	// 连线变量
	private String outcome;

	// 备注
	private String content;
}
