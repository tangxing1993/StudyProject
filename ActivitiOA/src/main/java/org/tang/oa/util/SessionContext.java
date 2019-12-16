package org.tang.oa.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tang.oa.system.domin.User;

/**
 * 
 * @date   2019年12月11日
 * @author tangxing
 * @desc   <p> session的工具类 </p>
 */
public abstract class SessionContext {

	public static final String LOGIN_USER = "loginUser";
	
	/**
	 * 
	 * @date 2019年12月11日
	 * @desc <p> 获取session </p>
	 * @return
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return session;
	}
	
	/**
	 * 
	 * @date 2019年12月11日
	 * @desc <p> 保存登录用户到session中 </p>
	 * @param user
	 */
	public static void saveLoginUser(User user) {
		getSession().setAttribute(LOGIN_USER, user);
	}
	
	/**
	 * 
	 * @date 2019年12月11日
	 * @desc <p> 获取登录的用户 </p>
	 * @return
	 */
	public static User getLoginUser() {
		return (User) Optional.ofNullable(getSession().getAttribute(LOGIN_USER)).orElse(null);
	}
	
}
