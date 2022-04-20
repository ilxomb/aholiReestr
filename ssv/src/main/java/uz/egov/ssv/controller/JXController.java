package uz.egov.ssv.controller;

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
import uz.egov.ssv.entity.JstmData;
import uz.egov.ssv.entity.JsvxData;
import uz.egov.ssv.entity.JsvxInfo;
import uz.egov.ssv.entity.JstmInfo;
import uz.egov.ssv.service.JMService;
import uz.egov.ssv.service.JXService;

import java.sql.*;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Соғлиқни сақлаш вазирлиги", description = "©")
public class JXController {
    @Autowired
    JXService jxService;

    @Autowired
    JMService jmService;


    //8.1. Жисмоний шахснинг тиббий маълумотларини етказиб бериш
    @PostMapping("/jm/add")
    @Operation(summary = "Тиббий маълумотнома киритиш", description = "Жисмоний шахснинг тиббий маълумотларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JstmInfo jstmInformation) throws Exception {
        return ResponseEntity.ok(jmService.save(jstmInformation));
    }

    @GetMapping("/jm/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JstmInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        List<JstmInfo> list = jmService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JstmInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jm/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг тиббий маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JstmInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
//        Date d1 = Date.valueOf(sana);
        return ResponseEntity.ok(jmService.findByDate(sana));
    }

    @GetMapping("/jm/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг тиббий маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JstmData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jmService.findByJSHSHIR(jshshir));
    }




    //8.2. Жисмоний шахснинг васийлик, ҳомийлик маълумотларини етказиб бериш
    @PostMapping("/jx/add")
    @Operation(summary = "Васийлик, ҳомийлик маълумот киритиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jx(@RequestBody JsvxInfo jsvxInformation){
        return ResponseEntity.ok(jxService.save(jsvxInformation));
    }
    @GetMapping("/jx/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvxInfo.class))))})
    public ResponseEntity<?> list_jx( @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsvxInfo> list = jxService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsvxInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/jx/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvxInfo.class))))})
    public ResponseEntity<?> list_jx_by_date(@PathVariable(name = "sana") java.sql.Date sana){
//        Date d1 = sana; //Date.valueOf(sana);
        return ResponseEntity.ok(jxService.findByDate(sana));
    }

    @GetMapping("/jx/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг васийлик, ҳомийлик маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvxData.class))))})
    public ResponseEntity<?> list_jx_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jxService.findByJSHSHIR(jshshir));
    }
}
