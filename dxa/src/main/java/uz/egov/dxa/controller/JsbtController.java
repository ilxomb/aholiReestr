package uz.egov.dxa.controller;

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
import uz.egov.ResponseJSBT;
import uz.egov.ResponseType;
import uz.egov.dxa.entity.*;
import uz.egov.dxa.entity.data.*;
import uz.egov.dxa.service.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Адлия вазирлиги (8.1.)", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотлар")

public class JsbtController {
    //8.1. ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни етказиб бериш
    @Autowired
    JsbtInfoService jsbtInfoService;
    @PostMapping("/jsbt/add")
    @Operation(summary = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни киритиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотлар етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseJSBT.class))))})
    public ResponseEntity<?> add_jsbt(@RequestBody JsbtInfo jsbtInformation) throws Exception {
        return ResponseEntity.ok(jsbtInfoService.save(jsbtInformation));
    }

    @GetMapping("/jsbt/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtInfo.class))))})
    public ResponseEntity<?> list_jsbt( @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsbtInfo> list = jsbtInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsbtInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsbt/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtInfo.class))))})
    public ResponseEntity<?> list_jsbt_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsbtInfoService.findByDate(sana));
    }

    @GetMapping("/jsbt/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtData.class))))})
    public ResponseEntity<?> list_jsbt_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsbtInfoService.findByJSHSHIR(jshshir));
    }
}
