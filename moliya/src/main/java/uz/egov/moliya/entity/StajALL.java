package uz.egov.moliya.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import uz.egov.entity.MainEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jspm_staj")
public class StajALL extends MainEntity implements Serializable {

    @JsonProperty("stajin1")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Stajin1;//Ҳисобга олинган меҳнат стажи (фаолият бошланган сана)	Date		[0..1]
    @JsonProperty("stajin2")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Stajin2;//Ҳисобга олинган меҳнат стажи (фаолият тамомланган сана)	Date		[0..1]
    @JsonProperty("stajout1")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Stajout1;//Ҳисобга олинмаган меҳнат стажи (фаолият бошланган сана)	Date		[0..1]
    @JsonProperty("stajout2")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Stajout2;//Ҳисобга олинмаган меҳнат стажи (фаолият тамомланган сана)	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JspmData data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        StajALL that = (StajALL) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}