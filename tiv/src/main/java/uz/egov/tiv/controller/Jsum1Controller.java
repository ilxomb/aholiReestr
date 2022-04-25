package uz.egov.tiv.controller;

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
import uz.egov.tiv.entity.Jsum1Data;
import uz.egov.tiv.entity.Jsum1Info;
import uz.egov.tiv.service.Jsum1Service;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ташқи ишлар вазирлиги (8.2.1)", description = "©")
public class Jsum1Controller {

    @Autowired
    Jsum1Service jsum1Service;

    //8.2. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    //8.2.1 Ўзбекистон Республикаси фуқароси ҳисобланган ва чет элда бўлган ўн олти ёшга тўлмаган шахсга нисбатан

    @PostMapping("/jsum1/add")
    @Operation(summary = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни киритиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш (ўн олти ёшга тўлмаган шахсга нисбатан)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody Jsum1Info jsum1Information) throws Exception {
        return ResponseEntity.ok(jsum1Service.save(jsum1Information));
    }

    @GetMapping("/jsum1/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsum1Info.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<Jsum1Info> list = jsum1Service.readAll(pageNo, pageSize);
        return new ResponseEntity<List<Jsum1Info>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsum1/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsum1Info.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsum1Service.findByDate(sana));
    }

    @GetMapping("/jsum1/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsum1Data.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsum1Service.findByJSHSHIR(jshshir));
    }
}