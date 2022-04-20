package uz.egov.oak.controller;

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
import uz.egov.oak.entity.JsidData;
import uz.egov.oak.entity.JsidInfo;
import uz.egov.oak.service.OAKService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ўзбекистон Республикаси Вазирлар Маҳкамаси ҳузуридаги Олий аттестация комиссияси", description = "©")
public class OAKController {

    @Autowired
    OAKService oakService;

    //8.1. Жисмоний шахснинг илмий даражаси, унвони тўғрисидаги маълумотларни етказиб бериш
    @PostMapping("/jh/add")
    @Operation(summary = "Илмий даражаси, унвони тўғрисида ахборотларни киритиш", description = "Жисмоний шахснинг илмий даражаси, унвони тўғрисидаги маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsidInfo jsidInformation) throws Exception {
        return ResponseEntity.ok(oakService.save(jsidInformation));
    }

    @GetMapping("/jh/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг илмий даражаси, унвони тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsidInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsidInfo> list = oakService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsidInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jh/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг илмий даражаси, унвони тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsidInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(oakService.findByDate(sana));
    }

    @GetMapping("/jh/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг илмий даражаси, унвони тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsidData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(oakService.findByJSHSHIR(jshshir));
    }
}