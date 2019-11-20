package org.tang.activiti.boot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
/**
 * 
 * @date   2019年11月20日
 * @author tangxing
 * @desc   <p> 自定义的拦截SessionFilter </p>
 */
public class SessionFilter extends HttpFilter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO 校验session
		
		super.doFilter(request, response, chain);
	}
	
}
