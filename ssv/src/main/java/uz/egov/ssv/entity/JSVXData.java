package uz.egov.ssv.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jsvx_data")
public class JSVXData extends AbsEntity implements Serializable {
    @JsonProperty("JSHSHIR")
    @Column(length = 14, nullable = false)
    private String JSHSHIR;

    @JsonProperty("VXJSHSHIR")
    @Column(length = 14, nullable = false)
    private String VXJSHSHIR;

    @JsonProperty("VXFIO")
    private String VXFIO;

    @JsonProperty("VXBDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date VXBDate;

    @JsonProperty("VXJoy")
    private String VXJoy;

    @JsonProperty("VXgraj")
    @Column(length = 3)
    private String VXgraj;

    @JsonProperty("VXDate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date VXDate;

    @JsonProperty("VXSrok")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date VXSrok;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    private JSVXInformation jsvxInformation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JSVXData jsvxData = (JSVXData) o;
        return getId() != null && Objects.equals(getId(), jsvxData.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
