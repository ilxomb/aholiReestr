package uz.egov.dpm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.egov.ResponseType;
import uz.egov.dpm.entity.Jsu4Data;
import uz.egov.dpm.entity.Jsu4Info;
import uz.egov.dpm.service.Jsu4Service;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Давлат персоналлаштириш маркази (8.3.4.)", description = "©")
public class Jsu4Controller {

    @Autowired
    Jsu4Service jsu4Service;

    //8.3. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    //8.3.4 Шахсни тасдиқловчи ҳақиқий бўлмаган ва янгидан расмийлаштирилган ҳужжатлар реквизитлари
    @PostMapping("/jsu4/add")
    @Operation(summary = "Шахсни тасдиқловчи ҳақиқий бўлмаган ва янгидан расмийлаштирилган ҳужжатлар реквизитлари ўзгартирилганлиги бўйича маълумотларни киритиш", description = "Шахсни тасдиқловчи ҳақиқий бўлмаган ва янгидан расмийлаштирилган ҳужжатлар реквизитлари ўзгартирилганлиги бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody Jsu4Info jsu4Information) throws Exception {
        return ResponseEntity.ok(jsu4Service.save(jsu4Information));
    }

    @GetMapping("/jsu4/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳақиқий бўлмаган ва янгидан расмийлаштирилган ҳужжатлар реквизитлари ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu4Info.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<Jsu4Info> list = jsu4Service.readAll(pageNo, pageSize);
        return new ResponseEntity<List<Jsu4Info>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsu4/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳақиқий бўлмаган ва янгидан расмийлаштирилган ҳужжатлар реквизитлари ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu4Info.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsu4Service.findByDate(sana));
    }

    @GetMapping("/jsu4/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳақиқий бўлмаган ва янгидан расмийлаштирилган ҳужжатлар реквизитлари ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu4Data.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsu4Service.findByJSHSHIR(jshshir));
    }
}