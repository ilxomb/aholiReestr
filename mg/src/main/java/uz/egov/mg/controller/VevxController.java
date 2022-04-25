package uz.egov.mg.controller;

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
import uz.egov.mg.entity.VevxData;
import uz.egov.mg.entity.VevxInfo;
import uz.egov.mg.service.VevxService;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Миллий гвардия", description = "©")
public class VevxController {

    @Autowired
    VevxService VevxService;

    //8.1. Вояга етмаганларга нисбатан белгиланган васийлик, ҳомийлик маълумотларини етказиб бериш
    @PostMapping("/Vevx/add")
    @Operation(summary = "Вояга етмаганларга нисбатан белгиланган васийлик, ҳомийлик маълумотларини киритиш", description = "Вояга етмаганларга нисбатан белгиланган васийлик, ҳомийлик маълумотларини етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody VevxInfo VevxInformation) throws Exception {
        return ResponseEntity.ok(VevxService.save(VevxInformation));
    }

    @GetMapping("/Vevx/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Вояга етмаганларга нисбатан белгиланган васийлик, ҳомийлик маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = VevxInfo.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<VevxInfo> list = VevxService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<VevxInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/Vevx/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Вояга етмаганларга нисбатан белгиланган васийлик, ҳомийлик маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = VevxInfo.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(VevxService.findByDate(sana));
    }

    @GetMapping("/Vevx/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Вояга етмаганларга нисбатан белгиланган васийлик, ҳомийлик маълумотларини олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = VevxData.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(VevxService.findByJSHSHIR(jshshir));
    }
}