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
import uz.egov.iiv.entity.JsxgData;
import uz.egov.iiv.entity.JsxgInfo;
import uz.egov.iiv.service.JsxgService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ички ишлар вазирлиги (8.2.)", description = "©")
public class JsxgController {

    @Autowired
    JsxgService jsxgService;

    //8.2. Ҳайдовчилик гувоҳномаси реквизитларини етказиб бериш
    @PostMapping("/jsxg/add")
    @Operation(summary = "Ҳайдовчилик гувоҳномаси реквизитларини киритиш", description = "Ҳайдовчилик гувоҳномаси реквизитларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsxgInfo jsxgInformation) throws Exception {
        return ResponseEntity.ok(jsxgService.save(jsxgInformation));
    }

    @GetMapping("/jsxg/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ҳайдовчилик гувоҳномаси реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsxgInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsxgInfo> list = jsxgService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsxgInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsxg/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ҳайдовчилик гувоҳномаси реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsxgInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsxgService.findByDate(sana));
    }

    @GetMapping("/jsxg/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ҳайдовчилик гувоҳномаси реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsxgData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsxgService.findByJSHSHIR(jshshir));
    }
}