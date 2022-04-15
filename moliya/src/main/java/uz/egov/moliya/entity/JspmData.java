package uz.egov.moliya.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.egov.entity.JshshirEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jspm_data")
public class JspmData extends JshshirEntity implements Serializable {

    @JsonProperty("pasn")
    @Column(length = 9)
    protected String PasN; //Паспорт cерияси ва рақами	String	9 та белги	[1]
    @JsonProperty("pdate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date PDate; //Пенсия (нафақа) тайинланган сана	Date		[1]
    @JsonProperty("psum")
    protected Integer PSum; //Тайинланган пенсия (нафақа) миқдори	Int		[1]
    @JsonProperty("ustaj")
    protected Integer Ustaj; //Жами иш стажи	Int		[1]
    @JsonProperty("uoylik")
    protected Integer Uoylik; //Ўртача ойлик иш хақи	Int		[1]
    @JsonProperty("pturi")
    @Column(length = 2)
    protected String Pturi; //Тайинланган пенсия (нафақа) тури	String	2 та белги, маълумотнома асосида	[1]
    @JsonProperty("psrok")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Psrok; //Тўлов муддати	Date		[0..1]

    @JsonProperty("StajALL")
    @OneToMany(mappedBy = "data", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    protected List<StajALL> StajALL; //Ҳисобга олинган ва олинмаган меҳнат стажи

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JspmInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JspmData that = (JspmData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
