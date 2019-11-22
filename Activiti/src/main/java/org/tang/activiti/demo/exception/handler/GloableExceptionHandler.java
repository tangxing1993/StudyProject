package org.tang.activiti.demo.exception.handler;
/**
 * 
 * @date   2019年11月21日
 * @author tangxing
 * @desc   <p> 全局异常处理器 </p>
 */

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GloableExceptionHandler {

	/**
	 * 
	 * @date 2019年11月21日
	 * @desc <p> Exception异常拦截处理 </p>
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public String Exception(Exception e) {
		log.error(e.getMessage(), e);
		return " 请求失败~ ";
	}
	
}
