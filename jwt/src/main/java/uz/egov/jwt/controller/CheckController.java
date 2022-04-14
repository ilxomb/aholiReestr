package uz.egov.jwt.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin()
@RequestMapping("/rest-api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Check JWT token", description = "Тизимнинг иш ҳолатини текшириш")
public class CheckController {


    @GetMapping({ "/check" })
    public String test() {
        return "System is up and running - " + new java.util.Date().toString();
    }

}