package uz.egov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DpmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DpmApplication.class, args);
    }

}
