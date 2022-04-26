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
import uz.egov.dpm.entity.JsxData;
import uz.egov.dpm.entity.JsxInfo;
import uz.egov.dpm.service.JsxService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Давлат персоналлаштириш маркази (8.2.)", description = "©")
public class JsxController {

    @Autowired
    JsxService jsxService;

    //8.2. Шахсни тасдиқловчи ҳужжатлар бўйича маълумотларни етказиб бериш
    @PostMapping("/jsx/add")
    @Operation(summary = "Шахсни тасдиқловчи ҳужжатлар бўйича маълумотларни киритиш", description = "Шахсни тасдиқловчи ҳужжатлар бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsxInfo jsxInformation) throws Exception {
        return ResponseEntity.ok(jsxService.save(jsxInformation));
    }

    @GetMapping("/jsx/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳужжатлар бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsxInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsxInfo> list = jsxService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsxInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsx/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳужжатлар бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsxInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsxService.findByDate(sana));
    }

    @GetMapping("/jsx/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Шахсни тасдиқловчи ҳужжатлар бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsxData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsxService.findByJSHSHIR(jshshir));
    }
}