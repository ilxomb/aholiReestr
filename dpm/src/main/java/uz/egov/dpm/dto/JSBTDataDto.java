package uz.egov.dpm.dto;

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
        "bjshshir",
        "bfam",
        "bism",
        "botch",
        "bdata"
})
@Generated("jsonschema2pojo")
@Data
public class JSBTDataDto {

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
