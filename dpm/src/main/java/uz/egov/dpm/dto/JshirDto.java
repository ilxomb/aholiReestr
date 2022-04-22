package uz.egov.dpm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JshirDto {

    @JsonProperty("information_date")
    public String informationDate;

    @JsonProperty("bjshshir")
    public String bjshshir;

    @JsonProperty("bfam")
    public String bfam;

    @JsonProperty("bism")
    public String bism;

    @JsonProperty("botch")
    public String botch;

    @JsonProperty("bdata")
    public String bdata;

}
