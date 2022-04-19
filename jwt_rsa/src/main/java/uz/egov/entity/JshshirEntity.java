package uz.egov.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class JshshirEntity extends MainEntity {
    @JsonProperty("jshshir")
    @Column(length = 14, nullable = false)
    private String jshshir;
}
