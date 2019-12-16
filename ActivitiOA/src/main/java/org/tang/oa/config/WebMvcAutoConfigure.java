package org.tang.oa.config;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.tang.oa.interceptor.CheckPrivilegeHandlerInterceptor;
/**
 * 
 * @date   2019年12月11日
 * @author tangxing
 * @desc   <p> 添加自定义的拦截器 </p>
 */
@Configuration
public class WebMvcAutoConfigure implements WebMvcConfigurer {

	@Autowired
	private CheckPrivilegeHandlerInterceptor checkPrivilegeHandlerInterceptor;
	@Autowired
	private ServletContext servletContext;
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(checkPrivilegeHandlerInterceptor)
		.addPathPatterns((List<String>)servletContext.getAttribute("allPrivilegeUrl"));
		//.addPathPatterns("/**")
		//.excludePathPatterns("/script/**","/style/**","/loginUI","/error","/login","/indexUI","/topUI","/leftUI","/bottomUI","/logout","/noPrivilegeUI","/");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
