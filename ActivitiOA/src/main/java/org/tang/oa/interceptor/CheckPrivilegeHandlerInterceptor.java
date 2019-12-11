package org.tang.oa.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.tang.oa.system.domin.User;
import org.tang.oa.util.SessionContext;
/**
 * 
 * @date   2019年12月11日
 * @author tangxing
 * @desc   <p> 校验权限拦截器 </p>
 */
@Component
public class CheckPrivilegeHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/**
		 *  - 判断当前用户是否登录
		 * 	 - 登录校验权限
		 *   	- 有权限放行
		 *      - 无权限重定向到无权限页面
		 *   - 未登录重定向到登录界面
		 */
		boolean preHandle = false;
		User loginUser = SessionContext.getLoginUser();
		if(loginUser != null) {
			String privilegeUrl = request.getRequestURI().replace(request.getContextPath(), "");
			if(loginUser.hasPrivilege(privilegeUrl)) {
				preHandle = HandlerInterceptor.super.preHandle(request, response, handler);
			}else {
				response.sendRedirect(request.getContextPath() + "/noPrivilegeUI");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/loginUI");
		}
		return preHandle;
	}

	
}
