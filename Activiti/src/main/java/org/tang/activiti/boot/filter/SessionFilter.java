package org.tang.activiti.boot.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.tang.activiti.demo.util.SessionContext;
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
			String url = ((HttpServletRequest)request).getRequestURI();
			if(!"/login".equals(url) && !url.contains("/lib/") && !url.contains("/h2-console")) {
				String loginUserName = SessionContext.getLoginUserName();
				if(StringUtils.isEmpty(loginUserName)) {
					((HttpServletResponse)response).sendRedirect("/login");
					return;
				}
			}
			super.doFilter(request, response, chain);
	}
	
}
