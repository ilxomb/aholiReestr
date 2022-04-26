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
import uz.egov.dpm.entity.JsbtData;
import uz.egov.dpm.entity.JsbtInfo;
import uz.egov.dpm.service.JsbtService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Давлат персоналлаштириш маркази (8.1.)", description = "©")
public class JsbtController {

    @Autowired
    JsbtService jsbtService;

    //8.1. Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларни етказиб бериш
    @PostMapping("/jsbt/add")
    @Operation(summary = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларни киритиш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsbtInfo jsbtInformation) throws Exception {
        return ResponseEntity.ok(jsbtService.save(jsbtInformation));
    }

    @GetMapping("/jsbt/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsbtInfo> list = jsbtService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsbtInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsbt/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsbtService.findByDate(sana));
    }

    @GetMapping("/jsbt/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Туғилиши рўйхатдан ўтказилган бола тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsbtService.findByJSHSHIR(jshshir));
    }
}