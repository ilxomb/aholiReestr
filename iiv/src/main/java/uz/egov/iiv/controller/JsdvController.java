package uz.egov.iiv.controller;

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
import uz.egov.iiv.entity.JsdvData;
import uz.egov.iiv.entity.JsdvInfo;
import uz.egov.iiv.service.JsdvService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ички ишлар вазирлиги (8.1.)", description = "©")
public class JsdvController {

    @Autowired
    JsdvService jsdvService;

    //8.1. Шахсни тасдиқловчи ҳужжатлар биринчи марта расмийлаштирилганда жисмоний шахснинг доимий ва вақтинчалик яшаш жойи бўйича рўйхатга олиш маълумотларини етказиб бериш
    @PostMapping("/jsdv/add")
    @Operation(summary = "Шахсни тасдиқловчи ҳужжатлар биринчи марта расмийлаштирилганда жисмоний шахснинг доимий ва вақтинчалик яшаш жойи бўйича рўйхатга олиш маълумотларини киритиш", description = "Шахсни тасдиқловчи ҳужжатлар биринчи марта расмийлаштирилганда жисмоний шахснинг доимий ва вақтинчалик яшаш жойи бўйича рўйхатга олиш маълумотларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsdvInfo jsdvInformation) throws Exception {
        return ResponseEntity.ok(jsdvService.save(jsdvInformation));
    }

    @GetMapping("/jsdv/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳужжатлар биринчи марта расмийлаштирилганда жисмоний шахснинг доимий ва вақтинчалик яшаш жойи бўйича рўйхатга олиш маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsdvInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsdvInfo> list = jsdvService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsdvInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsdv/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳужжатлар биринчи марта расмийлаштирилганда жисмоний шахснинг доимий ва вақтинчалик яшаш жойи бўйича рўйхатга олиш маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsdvInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsdvService.findByDate(sana));
    }

    @GetMapping("/jsdv/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳужжатлар биринчи марта расмийлаштирилганда жисмоний шахснинг доимий ва вақтинчалик яшаш жойи бўйича рўйхатга олиш маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsdvData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsdvService.findByJSHSHIR(jshshir));
    }
}