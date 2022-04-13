package uz.egov.ssv.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Comment",
        "DisabilityPercentage",
        "DisabilityReason",
        "DisabilityDateEnd",
        "DisabilityDateStart",
        "DisabilityGroup",
        "Ekriteria_1",
        "Ekriteria_2",
        "Ekriteria_4",
        "Ekriteria_6",
        "JSHSHIR",
        "Kgroup",
        "Krezus",
        "Ekriteria_7",
        "Epdate",
        "ReferenceSeries",
        "Ekriteria_5",
        "ReferenceNumber",
        "Ekriteria_3"
})
@Generated("jsonschema2pojo")
@Data
public class JSTMDataDto implements Serializable {

    @JsonProperty("Comment")
    public String comment;
    @JsonProperty("Disability_percentage")
    public Integer disabilityPercentage;
    @JsonProperty("DisabilityReason")
    public String disabilityReason;
    @JsonProperty("DisabilityDateEnd")
    public String disabilityDateEnd;
    @JsonProperty("DisabilityDateStart")
    public String disabilityDateStart;
    @JsonProperty("DisabilityGroup")
    public Integer disabilityGroup;
    @JsonProperty("Ekriteria_1")
    public Integer ekriteria_1;
    @JsonProperty("Ekriteria_2")
    public Integer ekriteria_2;
    @JsonProperty("Ekriteria_4")
    public Integer ekriteria_4;
    @JsonProperty("Ekriteria_6")
    public Integer ekriteria_6;
    @JsonProperty("JSHSHIR")
    public String jshshir;
    @JsonProperty("Kgroup")
    public String kgroup;
    @JsonProperty("Krezus")
    public String krezus;
    @JsonProperty("Ekriteria_7")
    public Integer ekriteria_7;
    @JsonProperty("Epdate")
    public String epdate;
    @JsonProperty("ReferenceSeries")
    public String referenceSeries;
    @JsonProperty("Ekriteria_5")
    public Integer ekriteria_5;
    @JsonProperty("ReferenceNumber")
    public String referenceNumber;
    @JsonProperty("Ekriteria_3")
    public Integer ekriteria_3;

    @JsonProperty("info_id")
    public Long info_id;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}