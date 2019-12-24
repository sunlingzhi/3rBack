package edu.njnu.opengms.mcclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableEurekaClient
@EnableAsync
@SpringBootApplication(scanBasePackages = {"edu.njnu.opengms.common","edu.njnu.opengms.mcclient"})
public class McClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(McClientApplication.class, args);
    }

}
