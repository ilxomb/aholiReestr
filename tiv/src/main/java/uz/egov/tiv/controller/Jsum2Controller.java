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
import uz.egov.tiv.entity.Jsum2Data;
import uz.egov.tiv.entity.Jsum2Info;
import uz.egov.tiv.service.Jsum2Service;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ташқи ишлар вазирлиги (8.2.2)", description = "©")
public class Jsum2Controller {

    @Autowired
    Jsum2Service jsum2Service;

    //8.2. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    //8.2.2 Ўзбекистон Республикаси фуқароси ҳисобланган ва чет элда бўлган ўн олти ёшга тўлган шахсга нисбатан

    @PostMapping("/jsum2/add")
    @Operation(summary = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни киритиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш (ўн олти ёшга тўлган шахсга нисбатан)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody Jsum2Info jsum2Information) throws Exception {
        return ResponseEntity.ok(jsum2Service.save(jsum2Information));
    }

    @GetMapping("/jsum2/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsum2Info.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<Jsum2Info> list = jsum2Service.readAll(pageNo, pageSize);
        return new ResponseEntity<List<Jsum2Info>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsum2/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsum2Info.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsum2Service.findByDate(sana));
    }

    @GetMapping("/jsum2/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsum2Data.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsum2Service.findByJSHSHIR(jshshir));
    }
}