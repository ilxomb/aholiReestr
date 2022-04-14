package uz.egov.jwt.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@Tag(name = "Синов", description = "Тизимнинг иш ҳолатини текшириш")
public class TestController {
    private static final Logger logger = LogManager.getLogger(TestController.class);

    @GetMapping({ "/test" })
    public String test() {
        return "System is up and running - " + new java.util.Date().toString();
    }

//    @GetMapping({ "/test/{prm}" })
//    public String test(@PathVariable(name = "prm") String prm) {
//        return "System is up and running - " + new java.util.Date().toString();
//    }
}