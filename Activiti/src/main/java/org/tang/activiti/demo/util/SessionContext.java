package org.tang.activiti.demo.util;
/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> session的上下文工具类 </p>
 */

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class SessionContext {

	public static final String GLOBLE_USER_NAME = "globle_user_name";
	
	/**
	 * 
	 * @date 2019年11月21日
	 * @desc <p> 获取Session </p>
	 * @return
	 */
	public static HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return session;
	}
	
	/**
	 * 
	 * @date 2019年11月21日
	 * @desc <p> session失效 </p>
	 */
	public static void invalidate() {
		getSession().invalidate();
	}
	
	/**
	 * 
	 * @date 2019年11月21日
	 * @desc <p> 保存登录用户 </p>
	 * @param name
	 */
	public static void saveLoginUser(String name) {
		getSession().setAttribute(GLOBLE_USER_NAME, name);
	} 

	/**
	 * 
	 * @date 2019年11月21日
	 * @desc <p> 获取登录用户 </p>
	 * @return
	 */
	public static String getLoginUserName() {
		return  (String) getSession().getAttribute(GLOBLE_USER_NAME);
	}
	
}
