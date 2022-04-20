package uz.egov.mudofa.controller;

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
import uz.egov.mudofa.entity.JshmData;
import uz.egov.mudofa.entity.JshmInfo;
import uz.egov.mudofa.service.JHService;
import java.sql.*;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Мудофаа вазирлиги", description = "©")
public class JHController {

    @Autowired
    JHService jhService;

    //8.1. Жисмоний шахснинг ҳарбий мажбурият ва ҳарбий хизматга оид маълумотларини етказиб бериш
    @PostMapping("/jh/add")
    @Operation(summary = "Ҳарбий мажбурият ва ҳарбий хизматга оид маълумот киритиш", description = "Жисмоний шахснинг ҳарбий мажбурият ва ҳарбий хизматга оид маълумотларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JshmInfo jshmInformation) throws Exception {
        return ResponseEntity.ok(jhService.save(jshmInformation));
    }

    @GetMapping("/jh/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ҳарбий мажбурият ва ҳарбий хизматга оид маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JshmInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JshmInfo> list = jhService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JshmInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jh/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ҳарбий мажбурият ва ҳарбий хизматга оид маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JshmInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jhService.findByDate(sana));
    }

    @GetMapping("/jh/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ҳарбий мажбурият ва ҳарбий хизматга оид маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JshmData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jhService.findByJSHSHIR(jshshir));
    }
}