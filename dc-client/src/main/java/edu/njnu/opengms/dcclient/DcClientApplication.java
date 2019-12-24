package edu.njnu.opengms.dcclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"edu.njnu.opengms.common","edu.njnu.opengms.dcclient"})
@EnableEurekaClient
public class DcClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DcClientApplication.class, args);
        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
            if(beanDefinitionName.equals("dataItemControllerImpl")){
                System.out.println(beanDefinitionName);
            }
        }
    }

}
