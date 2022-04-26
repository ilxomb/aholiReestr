package uz.egov.dpm.controller;

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
import uz.egov.dpm.entity.Jsu3Data;
import uz.egov.dpm.entity.Jsu3Info;
import uz.egov.dpm.service.Jsu3Service;

import java.sql.Date;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Давлат персоналлаштириш маркази (8.3.3.)", description = "©")
public class Jsu3Controller {

    @Autowired
    Jsu3Service jsu3Service;

    //8.3. Жисмоний шахсга оид маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш
    //8.3.3 Ўзбекистон Республикасида яшаш гувоҳномаси ва биометрик ҳаракатланиш ҳужжатини қайта расмийлаштиришда фуқаролиги бўлмаган шахс ва чет эл фуқаросининг ўзгартирилган маълумотлари
    @PostMapping("/jsu3/add")
    @Operation(summary = "Ўзбекистон Республикасида яшаш гувоҳномаси маълумотлар ўзгартирилганлиги бўйича маълумотларни киритиш", description = "Ўзбекистон Республикасида яшаш гувоҳномаси маълумотлар ўзгартирилганлиги бўйича маълумотларни етказиб бериш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ResponseType.class))))})
    public ResponseEntity<?> add_jm(@RequestBody Jsu3Info jsu3Information) throws Exception {
        return ResponseEntity.ok(jsu3Service.save(jsu3Information));
    }

    @GetMapping("/jsu3/list")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ўзбекистон Республикасида яшаш гувоҳномаси маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu3Info.class))))})
    public ResponseEntity<?> list_jm( @RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize){
        List<Jsu3Info> list = jsu3Service.readAll(pageNo, pageSize);
        return new ResponseEntity<List<Jsu3Info>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/jsu3/list/{sana}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ўзбекистон Республикасида яшаш гувоҳномаси маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu3Info.class))))})
    public ResponseEntity<?> list_jm_by_date(@PathVariable(name = "sana") Date sana) throws Exception {
        return ResponseEntity.ok(jsu3Service.findByDate(sana));
    }

    @GetMapping("/jsu3/find/{jshshir}")
    @Operation(summary = "Киритилган маълумотни олиш", description = "Ўзбекистон Республикасида яшаш гувоҳномаси маълумотлар ўзгартирилганлиги бўйича маълумотларни олиш")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jsu3Data.class))))})
    public ResponseEntity<?> list_jm_by_jshshir(@PathVariable(name = "jshshir") String jshshir){
        return ResponseEntity.ok(jsu3Service.findByJSHSHIR(jshshir));
    }
}