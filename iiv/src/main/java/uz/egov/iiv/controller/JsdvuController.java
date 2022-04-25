package uz.egov.iiv.controller;

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
import uz.egov.iiv.entity.JsdvuData;
import uz.egov.iiv.entity.JsdvuInfo;
import uz.egov.iiv.service.JsdvuService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ички ишлар вазирлиги (8.3.)", description = "©")
public class JsdvuController {

    @Autowired
    JsdvuService jsdvuService;

    //8.3. Жисмоний шахснинг доимий яшаш жойи ва/ёки вақтинчалик яшаш жойи бўйича рўйхатдан ўтишни ўзгартириш бўйича маълумотларни етказиб бериш
    @PostMapping("/jsdvu/add")
    @Operation(summary = "Жисмоний шахснинг доимий яшаш жойи ва/ёки вақтинчалик яшаш жойи бўйича рўйхатдан ўтишни ўзгартириш бўйича маълумотларни киритиш", description = "Жисмоний шахснинг доимий яшаш жойи ва/ёки вақтинчалик яшаш жойи бўйича рўйхатдан ўтишни ўзгартириш бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsdvuInfo jsdvuInformation) throws Exception {
        return ResponseEntity.ok(jsdvuService.save(jsdvuInformation));
    }

    @GetMapping("/jsdvu/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг доимий яшаш жойи ва/ёки вақтинчалик яшаш жойи бўйича рўйхатдан ўтишни ўзгартириш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsdvuInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsdvuInfo> list = jsdvuService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsdvuInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsdvu/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг доимий яшаш жойи ва/ёки вақтинчалик яшаш жойи бўйича рўйхатдан ўтишни ўзгартириш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsdvuInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsdvuService.findByDate(sana));
    }

    @GetMapping("/jsdvu/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг доимий яшаш жойи ва/ёки вақтинчалик яшаш жойи бўйича рўйхатдан ўтишни ўзгартириш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsdvuData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsdvuService.findByJSHSHIR(jshshir));
    }
}