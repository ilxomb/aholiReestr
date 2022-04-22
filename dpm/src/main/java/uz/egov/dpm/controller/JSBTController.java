package uz.egov.dpm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.egov.dpm.dto.JSBTInfoDto;
import uz.egov.dpm.entity.JSBTInformation;
import uz.egov.dpm.service.JSBTInfoService;

import java.util.Date;

@Controller
@CrossOrigin()
@SecurityRequirement(name = "JWT token")
@Tag(name = "Давлат персоналлаштириш маркази", description = "©")
@RequestMapping("/api/dpm")
public class JSBTController {
    @Autowired
    JSBTInfoService jsbtInfoService;

    @PostMapping("/add")
    @Operation(summary = "Бола тўғрисидаги маълумотларни етказиб бериш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларни етказиб бериш")
    public ResponseEntity<?> save(@RequestBody JSBTInfoDto jsbtInfoDto){
        System.out.println(jsbtInfoDto);
        return ResponseEntity.ok(jsbtInfoService.save(jsbtInfoDto));
    }

    @GetMapping
    @Operation(summary = "Киритилган маълумотни олиш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларини олиш")
    public ResponseEntity<?> getAllRecords(){
        return ResponseEntity.ok(jsbtInfoService.findAll());
    }

    @GetMapping("/{jshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларини олиш")
    public ResponseEntity<?> getByJshshir(@PathVariable String jshir){
        System.out.println(jshir);
        return ResponseEntity.ok(jsbtInfoService.findByBjshir(jshir));
    }

    @GetMapping("/byData")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларини олиш")
    public ResponseEntity<?> getByData(@RequestParam String data){
        System.out.println(data);
        return ResponseEntity.ok(jsbtInfoService.findByDate(data));
    }

}


//{
//        "information_date": "11.04.2022 01:14:31",
//        "JSBTData": [
//        {
//        "bjshshir": "12345678910234",
//        "bfam": "Бабакулов",
//        "bism": "Илхомжон",
//        "botch": "Халикулович",
//        "bdata": "10.07.1978"
//        },
//        {
//        "bjshshir": "98765432103214",
//        "bfam": "Холманов",
//        "bism": "Зафар",
//        "botch": "Исамахаматович",
//        "bdata": "13.07.1978"
//        },
//        {
//        "bjshshir": "96385274123654",
//        "bfam": "Каримов",
//        "bism": "Элёр",
//        "botch": "ХХХХХ",
//        "bdata": "11.08.1985"
//        },
//        {
//        "bjshshir": "74123654789654",
//        "bfam": "Алметов",
//        "bism": "Шерзод",
//        "botch": "Болтабоевич",
//        "bdata": "12.12.1978"
//        }
//        ]
//        }
