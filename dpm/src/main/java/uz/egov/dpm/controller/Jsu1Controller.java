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
import uz.egov.dpm.entity.Jsu1Data;
import uz.egov.dpm.entity.Jsu1Info;
import uz.egov.dpm.service.Jsu1Service;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Давлат персоналлаштириш маркази (8.3.1.)", description = "©")
public class Jsu1Controller {

    @Autowired
    Jsu1Service jsu1Service;

    //8.3. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    //8.3.1 Ўзбекистон Республикаси фуқароси, чет эл фуқароси ва фуқаролиги бўлмаган шахс ҳисобланган ва Ўзбекистон Республикасида яшаш гувоҳномасига эга бўлган ўн олти ёшга тўлган шахсга нисбатан
    @PostMapping("/jsu1/add")
    @Operation(summary = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни киритиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody Jsu1Info jsu1Information) throws Exception {
        return ResponseEntity.ok(jsu1Service.save(jsu1Information));
    }

    @GetMapping("/jsu1/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu1Info.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<Jsu1Info> list = jsu1Service.readAll(pageNo, pageSize);
        return new ResponseEntity<List<Jsu1Info>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsu1/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu1Info.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsu1Service.findByDate(sana));
    }

    @GetMapping("/jsu1/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu1Data.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsu1Service.findByJSHSHIR(jshshir));
    }
}