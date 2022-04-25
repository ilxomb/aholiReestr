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
import uz.egov.tiv.entity.JskxData;
import uz.egov.tiv.entity.JskxInfo;
import uz.egov.tiv.service.JskxService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ташқи ишлар вазирлиги (8.1.)", description = "©")
public class JskxController {

    @Autowired
    JskxService jskxService;

    //8.1. Консуллик ҳисобига қўйиш ва/ёки ҳисобдан чиқариш тўғрисидаги маълумотларни етказиб бериш
    @PostMapping("/jskx/add")
    @Operation(summary = "Консуллик ҳисобига қўйиш ва/ёки ҳисобдан чиқариш тўғрисидаги маълумотларни киритиш", description = "Консуллик ҳисобига қўйиш ва/ёки ҳисобдан чиқариш тўғрисидаги маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JskxInfo jskxInformation) throws Exception {
        return ResponseEntity.ok(jskxService.save(jskxInformation));
    }

    @GetMapping("/jskx/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Консуллик ҳисобига қўйиш ва/ёки ҳисобдан чиқариш тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JskxInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JskxInfo> list = jskxService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JskxInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jskx/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Консуллик ҳисобига қўйиш ва/ёки ҳисобдан чиқариш тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JskxInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jskxService.findByDate(sana));
    }

    @GetMapping("/jskx/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Консуллик ҳисобига қўйиш ва/ёки ҳисобдан чиқариш тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JskxData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jskxService.findByJSHSHIR(jshshir));
    }
}