package uz.egov.dpm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jsbt_data")
public class JSBTData extends AbsEntity implements Serializable {

    @JsonProperty("bjshshir")
    @Column(length = 14, nullable = false, unique = true)
    private String bjshshir;

    @JsonProperty("bfam")
    @Column(length = 100, nullable = false)
    private String bfam;

    @JsonProperty("bism")
    @Column(length = 100, nullable = false)
    private String bism;

    @JsonProperty("botch")
    @Column(length = 100, nullable = false)
    private String botch;

    @JsonProperty("bdata")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date bdata;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JSBTInformation information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JSBTData jsbtData = (JSBTData) o;
        return getId() != null && Objects.equals(getId(), jsbtData.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}
