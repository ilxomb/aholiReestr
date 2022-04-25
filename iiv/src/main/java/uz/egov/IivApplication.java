package uz.egov;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Elektron hukumat loyihalarini boshqarish markazi API", version = "2.0", description = "Elektron hukumat loyihalarini boshqarish markazi"))
@SecurityScheme(
        name = "JWT token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "Authentication: Bearer ****"
)
public class IivApplication {

    public static void main(String[] args) {
        SpringApplication.run(IivApplication.class, args);
    }

}
