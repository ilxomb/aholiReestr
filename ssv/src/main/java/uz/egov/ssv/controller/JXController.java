package uz.egov.ssv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.egov.ssv.entity.JSVXInformation;
import uz.egov.ssv.service.JXService;

import java.sql.Date;

@RestController
@CrossOrigin()
@RequestMapping("/api/jx")
@Tag(name = "Соғлиқни сақлаш вазирлиги", description = "©")
public class JXController {
    @Autowired
    JXService jxService;

    //8.2. Жисмоний шахснинг васийлик, ҳомийлик маълумотларини етказиб бериш
    @PostMapping("/add")
    @Operation(summary = "Васийлик, ҳомийлик маълумот киритиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини етказиб бериш")
    public ResponseEntity<?> saqla(@RequestBody JSVXInformation jsvxInformation){
        System.out.println("jsvxInformation = " + jsvxInformation);
        return ResponseEntity.ok(jxService.save(jsvxInformation));
    }
    @GetMapping("/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини олиш")
    public ResponseEntity<?> list(){
        return ResponseEntity.ok(jxService.readAll());
    }
    @GetMapping("/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини олиш")
    public ResponseEntity<?> list_by_date(@PathVariable(name = "sana") String sana){
        Date d1 = Date.valueOf(sana);
        return ResponseEntity.ok(jxService.findByDate(d1));
    }

    @GetMapping("/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини олиш")
    public ResponseEntity<?> list_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jxService.findByJSHSHIR(jshshir));
    }
}
