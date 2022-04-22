package uz.egov.jwt_rsa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping("/rest-api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Синов", description = "Тизимнинг иш ҳолатини текшириш")
public class CheckController {

    @GetMapping({ "/check" })
    @Operation(summary = "JWT token тизимнинг иш ҳолатини текшириш", description = "JWT token тизимнинг иш ҳолатини текшириш")
    public String test() {
        return "System is up and running - " + new java.util.Date().toString();
    }

}