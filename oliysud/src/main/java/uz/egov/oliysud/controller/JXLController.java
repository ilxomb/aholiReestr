package uz.egov.oliysud.controller;

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
import uz.egov.oliysud.entity.JsmlData;
import uz.egov.oliysud.entity.JsmlInfo;
import uz.egov.oliysud.entity.JsoxmData;
import uz.egov.oliysud.entity.JsoxmInfo;
import uz.egov.oliysud.service.JLService;
import uz.egov.oliysud.service.JXService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Соғлиқни сақлаш вазирлиги", description = "©")
public class JXLController {

    @Autowired
    JLService jlService;

    @Autowired
    JXService jxService;


    //8.1. Субъектни муомалага лаёқатсиз ёки муомала лаёқати чекланган деб тан олиш тўғрисидаги суд қарорининг реквизитларини етказиб бериш
    @PostMapping("/jl/add")
    @Operation(summary = "Субъектни муомалага лаёқати ҳақидаги маълумот киритиш", description = "Субъектни муомалага лаёқатсиз ёки муомала лаёқати чекланган деб тан олиш тўғрисидаги суд қарорининг реквизитларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody JsmlInfo jsmlInformation) throws Exception {
        return ResponseEntity.ok(jlService.save(jsmlInformation));
    }

    @GetMapping("/jl/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Субъектни муомалага лаёқатсиз ёки муомала лаёқати чекланган деб тан олиш тўғрисидаги суд қарорининг реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmlInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsmlInfo> list = jlService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsmlInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jl/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Субъектни муомалага лаёқатсиз ёки муомала лаёқати чекланган деб тан олиш тўғрисидаги суд қарорининг реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmlInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
//        Date d1 = Date.valueOf(sana);
        return ResponseEntity.ok(jlService.findByDate(sana));
    }

    @GetMapping("/jl/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Субъектни муомалага лаёқатсиз ёки муомала лаёқати чекланган деб тан олиш тўғрисидаги суд қарорининг реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsmlData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jlService.findByJSHSHIR(jshshir));
    }

    //8.2. Ота-оналик ҳуқуқидан, фарзандликка олиш ҳуқуқидан маҳрум қилиш (чеклаш) тўғрисидаги суд қарорининг реквизитларини етказиб бериш
    @PostMapping("/jx/add")
    @Operation(summary = "Ота-оналик ҳуқуқидан, фарзандликка олиш ҳуқуқидан маҳрум қилиш (чеклаш) тўғрисидаги маълумот киритиш", description = "Ота-оналик ҳуқуқидан, фарзандликка олиш ҳуқуқидан маҳрум қилиш (чеклаш) тўғрисидаги суд қарорининг реквизитларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jx(@RequestBody JsoxmInfo jsoxmInformation){
        return ResponseEntity.ok(jxService.save(jsoxmInformation));
    }
    @GetMapping("/jx/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ота-оналик ҳуқуқидан, фарзандликка олиш ҳуқуқидан маҳрум қилиш (чеклаш) тўғрисидаги суд қарорининг реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsoxmInfo.class))))})
    public ResponseEntity<?> list_jx( @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsoxmInfo> list = jxService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsoxmInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/jx/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ота-оналик ҳуқуқидан, фарзандликка олиш ҳуқуқидан маҳрум қилиш (чеклаш) тўғрисидаги суд қарорининг реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsoxmInfo.class))))})
    public ResponseEntity<?> list_jx_by_date(@PathVariable(name = "sana") Date sana){
//        Date d1 = sana; //Date.valueOf(sana);
        return ResponseEntity.ok(jxService.findByDate(sana));
    }

    @GetMapping("/jx/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ота-оналик ҳуқуқидан, фарзандликка олиш ҳуқуқидан маҳрум қилиш (чеклаш) тўғрисидаги суд қарорининг реквизитларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsoxmData.class))))})
    public ResponseEntity<?> list_jx_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jxService.findByJSHSHIR(jshshir));
    }
}
