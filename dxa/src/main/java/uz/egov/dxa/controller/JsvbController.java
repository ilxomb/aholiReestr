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
import uz.egov.dxa.entity.JsfoInfo;
import uz.egov.dxa.entity.JsumInfo;
import uz.egov.dxa.entity.JsvbInfo;
import uz.egov.dxa.entity.data.JsfoData;
import uz.egov.dxa.entity.data.JsumData;
import uz.egov.dxa.entity.data.JsvbData;
import uz.egov.dxa.service.JsfoInfoService;
import uz.egov.dxa.service.JsumInfoService;
import uz.egov.dxa.service.JsvbInfoService;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Адлия вазирлиги (8.4.)", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотлар")

public class JsvbController {

    //8.4. Жисмоний шахснинг ўлими тўғрисидаги маълумотларни етказиб бериш
    @Autowired
    JsvbInfoService jsvbInfoService;
    @PostMapping("/jsvb/add")
    @Operation(summary = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни киритиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотлар етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsvb(@RequestBody JsvbInfo jsvbInformation) throws Exception {
        return ResponseEntity.ok(jsvbInfoService.save(jsvbInformation));
    }

    @GetMapping("/jsvb/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvbInfo.class))))})
    public ResponseEntity<?> list_jsvb( @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsvbInfo> list = jsvbInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsvbInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsvb/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvbInfo.class))))})
    public ResponseEntity<?> list_jsvb_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsvbInfoService.findByDate(sana));
    }

    @GetMapping("/jsvb/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvbData.class))))})
    public ResponseEntity<?> list_jsvb_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsvbInfoService.findByJSHSHIR(jshshir));
    }
}
