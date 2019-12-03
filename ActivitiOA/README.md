基于Activiti的OA案例项目

**__项目架构__**

	Maven + SpringBoot + Activiti + H2 + JPA + Thymeleaf

**__项目运行__**

	cd ${project_home}
	mvn clean spring-boot:run
	访问数据库: http://localhost/oa/h2-console   sa/123456
	访问应用: http://localhost/oa
	
**__项目模块__**

- 系统模块
	
	1. 用户模块
		
		1.1 查看用户列表
		
		1.2 用户添加

		1.3 用户修改
		
		1.4 用户删除
		
		1.5 初始化用户密码
		
	2. 岗位模块
		
		2.1 查看岗位列表
		
		2.2 岗位添加
		
		2.3 岗位修改
		
		2.4 岗位删除
		
		2.5 权限模块 (编辑)
	
	3. 部门模块
	
		3.1 查看部门列表
		
		3.2 部门添加

		3.3 部门修改
		
		3.4 部门删除
		
- 论坛模块
	
- 审批模块