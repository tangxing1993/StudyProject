package org.tang.oa;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
/**
 * 
 * @date   2019年11月30日
 * @author tangxing
 * @desc   <p> 项目启动入口 </p>
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ServletComponentScan  // 开启servlet注解扫描
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	
}
