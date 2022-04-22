package uz.egov.ssv.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import uz.egov.entity.JshshirEntity;

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
public class JsvxData extends JshshirEntity implements Serializable {
//    @JsonProperty("jshshir")
//    @Column(length = 14, nullable = false)
//    private String jshshir;

    @JsonProperty("vxjshshir")
    @Column(length = 14, nullable = false)
    private String vxjshshir;

    @JsonProperty("vxfio")
    @Column(length = 255)
    private String vxfio;

    @JsonProperty("vxbdate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date vxbdate;

    @JsonProperty("vxjoy")
    @Column(length = 255)
    private String vxjoy;

    @JsonProperty("vxgraj")
    @Column(length = 3)
    private String vxgraj;

    @JsonProperty("vxdate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date vxdate;

    @JsonProperty("vxsrok")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date vxsrok;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsvxInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsvxData jsvxData = (JsvxData) o;
        return getId() != null && Objects.equals(getId(), jsvxData.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
