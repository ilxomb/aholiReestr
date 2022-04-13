package uz.egov.ssv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.egov.ssv.dto.JstmInfoDTO;
import uz.egov.ssv.entity.JstmInfo;
import uz.egov.ssv.service.SsvService;

@Controller
@CrossOrigin()
@RequestMapping("/api")
public class SsvController {
    @Autowired
    SsvService ssvService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody JstmInfoDTO jstmDtoInfo){
        System.out.println(jstmDtoInfo);
        return ResponseEntity.ok(ssvService.save(jstmDtoInfo)).getBody();
    }

}
