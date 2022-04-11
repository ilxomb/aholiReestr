package uz.egov.iiv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class IivApplication {

    public static void main(String[] args) {
        SpringApplication.run(IivApplication.class, args);
    }

}
