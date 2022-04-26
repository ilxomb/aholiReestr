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
import uz.egov.dpm.entity.Jsu2Data;
import uz.egov.dpm.entity.Jsu2Info;
import uz.egov.dpm.service.Jsu2Service;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Давлат персоналлаштириш маркази (8.3.2.)", description = "©")
public class Jsu2Controller {

    @Autowired
    Jsu2Service jsu2Service;

    //8.3. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    //8.3.2 Биометрик паспорт (ID-карта), шу жумладан чет элга чиқиш учун паспорт алмашганда Ўзбекистон Республикаси фуқаросининг ўзгартирилган маълумотлари
    @PostMapping("/jsu2/add")
    @Operation(summary = "Биометрик паспорт (ID-карта) маълумотлар ўзгартирилганлиги бўйича маълумотларни киритиш", description = "Биометрик паспорт (ID-карта) маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody Jsu2Info jsu2Information) throws Exception {
        return ResponseEntity.ok(jsu2Service.save(jsu2Information));
    }

    @GetMapping("/jsu2/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Биометрик паспорт (ID-карта) маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu2Info.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<Jsu2Info> list = jsu2Service.readAll(pageNo, pageSize);
        return new ResponseEntity<List<Jsu2Info>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsu2/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Биометрик паспорт (ID-карта) маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu2Info.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsu2Service.findByDate(sana));
    }

    @GetMapping("/jsu2/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Биометрик паспорт (ID-карта) маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu2Data.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsu2Service.findByJSHSHIR(jshshir));
    }
}