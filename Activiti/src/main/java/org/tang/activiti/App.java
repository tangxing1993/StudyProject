package org.tang.activiti;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  <p> Activiti的启动文件 </p>
 *
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class App 
{
    public static void main( String[] args )
    {
       SpringApplication.run(App.class, args);
    }
}
