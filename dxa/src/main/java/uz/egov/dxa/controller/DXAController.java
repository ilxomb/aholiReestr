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
@Tag(name = "Адлия вазирлиги", description = "©")
public class DXAController {

    @Autowired
    JsbtInfoService jsbtInfoService;
    //8.1. ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни етказиб бериш
    @PostMapping("/jsbt/add")
    @Operation(summary = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни киритиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотлар етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseJSBT.class))))})
    public ResponseEntity<?> add_jsbt(@RequestBody JsbtInfo jsbtInformation) throws Exception {
        return ResponseEntity.ok(jsbtInfoService.save(jsbtInformation));
    }

    @GetMapping("/jsbt/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtInfo.class))))})
    public ResponseEntity<?> list_jsbt( @RequestParam(defaultValue = "0") Integer pageNo,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsbtInfo> list = jsbtInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsbtInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsbt/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtInfo.class))))})
    public ResponseEntity<?> list_jsbt_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsbtInfoService.findByDate(sana));
    }

    @GetMapping("/jsbt/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "ФҲДЁ органи сўровига кўра бола туғилиши рўйхатдан ўтказилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsbtData.class))))})
    public ResponseEntity<?> list_jsbt_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsbtInfoService.findByJSHSHIR(jshshir));
    }


    @Autowired
    JsfoInfoService jsfoInfoService;
    //8.2. Қариндошлар тўғрисидаги маълумотларни етказиб бериш
    @PostMapping("/jsfo/add")
    @Operation(summary = "Қариндошлар тўғрисидаги маълумотларни киритиш", description = "Қариндошлар тўғрисидаги маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsfo(@RequestBody JsfoInfo jsfoInformation) throws Exception {
        return ResponseEntity.ok(jsfoInfoService.save(jsfoInformation));
    }

    @GetMapping("/jsfo/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Қариндошлар тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsfoInfo.class))))})
    public ResponseEntity<?> list_jsfo( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsfoInfo> list = jsfoInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsfoInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsfo/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Қариндошлар тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsfoInfo.class))))})
    public ResponseEntity<?> list_jsfo_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsfoInfoService.findByDate(sana));
    }

    @GetMapping("/jsfo/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Қариндошлар тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsfoData.class))))})
    public ResponseEntity<?> list_jsfo_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsfoInfoService.findByJSHSHIR(jshshir));
    }

    @Autowired
    JsqmInfoService jsqmInfoService;
    //8.3. Фарзандликка олиш бўйича маълумотларни етказиб бериш
    @PostMapping("/jsqm/add")
    @Operation(summary = "Фарзандликка олиш бўйича маълумотларни киритиш", description = "Фарзандликка олиш бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsqm(@RequestBody JsqmInfo jsqmInformation) throws Exception {
        return ResponseEntity.ok(jsqmInfoService.save(jsqmInformation));
    }

    @GetMapping("/jsqm/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Фарзандликка олиш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsqmInfo.class))))})
    public ResponseEntity<?> list_jsqm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsqmInfo> list = jsqmInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsqmInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsqm/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Фарзандликка олиш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsqmInfo.class))))})
    public ResponseEntity<?> list_jsqm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsqmInfoService.findByDate(sana));
    }

    @GetMapping("/jsqm/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Фарзандликка олиш бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsqmData.class))))})
    public ResponseEntity<?> list_jsqm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsqmInfoService.findByJSHSHIR(jshshir));
    }



    @Autowired
    JsumInfoService jsumInfoService;
    //8.4. Жисмоний шахснинг ўлими тўғрисидаги маълумотларни етказиб бериш
    @PostMapping("/jsum/add")
    @Operation(summary = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни киритиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотлар етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsum(@RequestBody JsumInfo jsumInformation) throws Exception {
        return ResponseEntity.ok(jsumInfoService.save(jsumInformation));
    }

    @GetMapping("/jsum/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsumInfo.class))))})
    public ResponseEntity<?> list_jsum( @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsumInfo> list = jsumInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsumInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsum/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsumInfo.class))))})
    public ResponseEntity<?> list_jsum_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsumInfoService.findByDate(sana));
    }

    @GetMapping("/jsum/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахснинг ўлими тўғрисидаги маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsumData.class))))})
    public ResponseEntity<?> list_jsum_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsumInfoService.findByJSHSHIR(jshshir));
    }


    @Autowired
    JsvbInfoService jsvbInfoService;
    //8.5. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    @PostMapping("/jsvb/add")
    @Operation(summary = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни маълумотларни киритиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jsvb(@RequestBody JsvbInfo jsvbInformation) throws Exception {
        return ResponseEntity.ok(jsvbInfoService.save(jsvbInformation));
    }

    @GetMapping("/jsvb/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvbInfo.class))))})
    public ResponseEntity<?> list_jsvb( @RequestParam(defaultValue = "0") Integer pageNo,
                                        @RequestParam(defaultValue = "10") Integer pageSize){
        List<JsvbInfo> list = jsvbInfoService.readAll(pageNo, pageSize);
        return new ResponseEntity<List<JsvbInfo>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsvb/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvbInfo.class))))})
    public ResponseEntity<?> list_jsvb_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsvbInfoService.findByDate(sana));
    }

    @GetMapping("/jsvb/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JsvbData.class))))})
    public ResponseEntity<?> list_jsvb_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsvbInfoService.findByJSHSHIR(jshshir));
    }

}
