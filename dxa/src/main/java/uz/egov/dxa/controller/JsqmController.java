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
@Tag(name = "Адлия вазирлиги (8.2.)", description = "Қариндошлар тўғрисидаги маълумотлар")

public class JsqmController {
    //8.2. Қариндошлар тўғрисидаги маълумотларни етказиб бериш
    @Autowired
    JsqmInfoService jsqmInfoService;
    @PostMapping("/jsqm/add")
    @Operation(summary = "Қариндошлар тўғрисидаги маълумотларни киритиш", description = "Қариндошлар тўғрисидаги маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsqm(@RequestBody JsqmInfo jsqmInformation) throws Exception {
        return ResponseEntity.ok(jsqmInfoService.save(jsqmInformation));
    }

    @GetMapping("/jsqm/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Қариндошлар тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsqmInfo.class))))})
    public ResponseEntity<?> list_jsqm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsqmInfo> list = jsqmInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsqmInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsqm/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Қариндошлар тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsqmInfo.class))))})
    public ResponseEntity<?> list_jsqm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsqmInfoService.findByDate(sana));
    }

    @GetMapping("/jsqm/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Қариндошлар тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsqmData.class))))})
    public ResponseEntity<?> list_jsqm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsqmInfoService.findByJSHSHIR(jshshir));
    }
}
