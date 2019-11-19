package org.tang.activiti.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * 
 * @date   2019年11月19日
 * @author tangxing
 * @desc   <p> Jpa配置 </p>
 */
@Configuration
@EnableJpaRepositories(basePackages = "org.tang.activiti.demo.repository") // 启用JPA
public class JpaAutoConfiguration{

}
