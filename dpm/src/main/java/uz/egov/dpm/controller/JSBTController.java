package uz.egov.dpm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.egov.dpm.dto.JSBTInfoDto;
import uz.egov.dpm.entity.JSBTInformation;
import uz.egov.dpm.service.JSBTInfoService;

@Controller
@CrossOrigin()
@RequestMapping("/api/dpm")
public class JSBTController {
    @Autowired
    JSBTInfoService jsbtInfoService;

    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody JSBTInfoDto jsbtInfoDto){
        System.out.println(jsbtInfoDto);
        return ResponseEntity.ok(jsbtInfoService.save(jsbtInfoDto));
    }

//    @GetMapping
//    public ResponseEntity<?> getAllRecords(){
//        return ResponseEntity.ok(jsbtInfoService.findAll());
//    }

    @GetMapping("/byJshir")
    public JSBTInformation getByJshshir(@RequestParam String jshir){
        System.out.println(jshir);
        return jsbtInfoService.findByBjshir(jshir);
    }


}
