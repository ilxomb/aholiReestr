package uz.egov.ssv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class SsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsvApplication.class, args);
    }

}
