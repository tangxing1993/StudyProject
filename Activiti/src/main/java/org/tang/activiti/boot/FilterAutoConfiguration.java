package org.tang.activiti.boot;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 过滤器注册 </p>
 */
import org.tang.activiti.boot.filter.SessionFilter;
@Configuration
public class FilterAutoConfiguration {
	
	@Bean
	public FilterRegistrationBean<SessionFilter> createSessionFilter(){
		FilterRegistrationBean<SessionFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new SessionFilter());
		filter.setOrder(1);
		filter.setUrlPatterns(Arrays.asList("/*"));
		return filter;
	}
	
}
