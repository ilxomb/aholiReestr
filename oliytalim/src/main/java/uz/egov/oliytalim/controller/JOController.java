package uz.egov.oliytalim.controller;

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
import uz.egov.oliytalim.entity.JsmaData;
import uz.egov.oliytalim.entity.JsmaInfo;
import uz.egov.oliytalim.service.JOService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Олий ва ўрта махсус таълим вазирлиги", description = "©")
public class JOController {

    @Autowired
    JOService joService;

    //8.1. Жисмоний шахснинг маълумоти тўғрисида ахборотларни етказиб бериш
    @PostMapping("/jh/add")
    @Operation(summary = "Маълумоти тўғрисида ахборотларни киритиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsmaInfo jsmaInformation) throws Exception {
        return ResponseEntity.ok(joService.save(jsmaInformation));
    }

    @GetMapping("/jh/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmaInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsmaInfo> list = joService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsmaInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jh/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmaInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(joService.findByDate(sana));
    }

    @GetMapping("/jh/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг маълумоти тўғрисида ахборотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmaData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(joService.findByJSHSHIR(jshshir));
    }
}