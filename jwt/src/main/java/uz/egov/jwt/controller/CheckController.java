package uz.egov.jwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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