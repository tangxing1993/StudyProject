package org.tang.oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tang.oa.system.domin.Privilege;
import org.tang.oa.system.service.PrivilegeService;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @date   2019年12月5日
 * @author tangxing
 * @desc   <p> 服务启动初始化缓存权限数据到内存中 </p>
 */
@WebListener
@Slf4j
public class OAInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		PrivilegeService perivilegeService = WebApplicationContextUtils//
											.getWebApplicationContext(sce.getServletContext())//
											.getBean(PrivilegeService.class);
		List<Privilege> topPrivilegeList = perivilegeService.listForTopPrivilege();
		sce.getServletContext().setAttribute("topPrivilegeList", topPrivilegeList);
		log.info("=============== 权限数据缓存成功 =================");
		// 缓存所有的权限URl
		List<String> allPrivilegeUrl = perivilegeService.getAllPrivilegeUrl();
		sce.getServletContext().setAttribute("allPrivilegeUrl", allPrivilegeUrl);
		log.info("=============== 权限URL缓存成功 =================");
	}

	
}
