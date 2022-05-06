package uz.egov.jwt_rsa.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.egov.ConditionJSHSHIRType;


@RestController
@CrossOrigin()
@RequestMapping("/rest-api")
@SecurityRequirement(name = "JWT token")
@Tag(name = "Ҳолатини аниқлаш", description = "©")
public class ConditionController {

    //8.*. ЖШШИР ҳолатини аниқлаш
    @GetMapping(value = "/condition/{JSHSHIR}")
    @Operation(summary = "ЖШШИР ҳолатини аниқлаш", description = "ЖШШИР ҳолатини аниқлаш")
    public ResponseEntity<ConditionJSHSHIRType> condition(@PathVariable(name = "JSHSHIR") String JSHSHIR) throws Exception {
        ConditionJSHSHIRType conditionJSHSHIR = new ConditionJSHSHIRType();

//*******
        conditionJSHSHIR.setJSHSHIR(JSHSHIR);
        conditionJSHSHIR.setStatus(1);
//*******


        return ResponseEntity.ok(conditionJSHSHIR);
    }
}
