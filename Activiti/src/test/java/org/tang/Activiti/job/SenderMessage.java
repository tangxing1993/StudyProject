package org.tang.Activiti.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
/**
 * 
 * <p>2019年12月20日</p>
 * @author tangxing
 * <p> 发送短消息任务 </p>
 */
@Component("senderMessage")
public class SenderMessage implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) {
		Object object = execution.getVariable("count");
		String timeout = (String) execution.getVariable("timeout");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		if(object ==null) {
			execution.setVariable("count", 1);
			execution.setVariable("timeout", LocalDateTime.parse(timeout).plusSeconds(20l).format(formatter));
		}else {
			// execution.setVariable("count", 1);
			execution.setVariable("timeout", LocalDateTime.parse(timeout).plusYears(1000).format(formatter));
		}
		System.out.println(" 发送短信--------------------> " + LocalDateTime.now());
	}

}
