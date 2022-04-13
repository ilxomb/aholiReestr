package uz.egov.ssv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.egov.ssv.entity.JSVXInformation;
import uz.egov.ssv.service.JXService;

@Controller
@CrossOrigin()
@RequestMapping("/api/jx")
public class JXController {
    @Autowired
    JXService jxService;

    @PostMapping("/add")
    public String saqla(@RequestBody JSVXInformation jsvxInformation){
        System.out.println("jsvxInformation = " + jsvxInformation);
        return jxService.save(jsvxInformation);
    }


}
