package uz.egov.dpm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JshirDto {

    @JsonProperty("information_date")
    public String informationDate;
    private String jshshir;
}
