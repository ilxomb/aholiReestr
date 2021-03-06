package uz.egov.moliya.controller;

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
import uz.egov.moliya.entity.JspmData;
import uz.egov.moliya.entity.JspmInfo;
import uz.egov.moliya.service.JPService;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Соғлиқни сақлаш вазирлиги", description = "©")
public class JPController {
    @Autowired
    JPService jpService;

    //8.1. Жисмоний шахсга ҳисобланган пенсия ва нафақа ҳақидаги маълумотларни етказиб бериш
    @PostMapping("/jp/add")
    @Operation(summary = "Пенсия ва нафақа ҳақидаги маълумот киритиш", description = "Жисмоний шахсга ҳисобланган пенсия ва нафақа ҳақидаги маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JspmInfo JspmInformation) throws Exception {
        return ResponseEntity.ok(jpService.save(JspmInformation));
    }

    @GetMapping("/jp/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга ҳисобланган пенсия ва нафақа ҳақидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JspmInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        List<JspmInfo> list = jpService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JspmInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jp/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг тиббий маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JspmInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
//        System.out.println("sana: " + sana);
//        Date d1 = Date.valueOf(sana);
//        System.out.println("d1: " + d1);
        return ResponseEntity.ok(jpService.findByDate(sana));
    }

    @GetMapping("/jp/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг тиббий маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JspmData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jpService.findByJSHSHIR(jshshir));
    }
}
