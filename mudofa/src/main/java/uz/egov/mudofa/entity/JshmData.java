package uz.egov.mudofa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
@Table(name = "jshm_data")
public class JshmData extends JshshirEntity implements Serializable {

    @JsonProperty("hhindate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date HHinDate; //Ҳарбий ҳисобга биринчи марта қўйилган сана	Date		[1]
    @JsonProperty("hhoutdate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date HHoutDate; //Ҳарбий ҳисобдан охирги марта чиқарилган (четлатилган) сана	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JshmInfo information;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JshmData that = (JshmData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
