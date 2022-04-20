package uz.egov.xalqtaim.controller;

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
import uz.egov.xalqtaim.entity.JsmaData;
import uz.egov.xalqtaim.entity.JsmaInfo;
import uz.egov.xalqtaim.service.JEService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Халқ таълим вазирлиги", description = "©")
public class JEController {

    @Autowired
    JEService jeService;

    //8.1. Жисмоний шахснинг маълумоти тўғрисида ахборотларни етказиб бериш
    @PostMapping("/je/add")
    @Operation(summary = "Маълумоти тўғрисида ахборотларни киритиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsmaInfo jsmaInformation) throws Exception {
        return ResponseEntity.ok(jeService.save(jsmaInformation));
    }

    @GetMapping("/je/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmaInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsmaInfo> list = jeService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsmaInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/je/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmaInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jeService.findByDate(sana));
    }

    @GetMapping("/je/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmaData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jeService.findByJSHSHIR(jshshir));
    }
}