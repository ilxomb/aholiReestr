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
import uz.egov.ResponseType;
import uz.egov.dxa.entity.JsumInfo;
import uz.egov.dxa.entity.JsvbInfo;
import uz.egov.dxa.entity.data.JsumData;
import uz.egov.dxa.entity.data.JsvbData;
import uz.egov.dxa.service.JsumInfoService;
import uz.egov.dxa.service.JsvbInfoService;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Адлия вазирлиги (8.5.)", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотлар")

public class JsumController {

    //8.5. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    @Autowired
    JsumInfoService jsumInfoService;
    @PostMapping("/jsum/add")
    @Operation(summary = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни маълумотларни киритиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsum(@RequestBody JsumInfo jsumInformation) throws Exception {
        return ResponseEntity.ok(jsumInfoService.save(jsumInformation));
    }

    @GetMapping("/jsum/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsumInfo.class))))})
    public ResponseEntity<?> list_jsum( @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsumInfo> list = jsumInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsumInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsum/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsumInfo.class))))})
    public ResponseEntity<?> list_jsum_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsumInfoService.findByDate(sana));
    }

    @GetMapping("/jsum/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsumData.class))))})
    public ResponseEntity<?> list_jsum_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsumInfoService.findByJSHSHIR(jshshir));
    }

}
