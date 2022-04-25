package uz.egov.iiv.entity;

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
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jsxg_data")
public class JsxgData extends JshshirEntity implements Serializable {

    @JsonProperty("gsr")
    @Column(length = 10)
    protected String GSR; //Гувоҳнома серия ва рақами	String	10 та белги	[1]
    @JsonProperty("gdata")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date Gdata; //Гувоҳнома берилган сана	Date		[1]
    @JsonProperty("gsrok")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date GSrok; //Гувоҳноманинг амал қилиш муддати	Date		[1]
    @JsonProperty("gname")
    @Column(length = 3)
    protected String GName; //Гувоҳномани берган давлат органининг номи	String	3 та белги, маълумотнома асосида 	[1]

    @JsonProperty("TOIFA")
    @OneToMany(mappedBy = "data", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<JsxgToifa> jsxgToifa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsxgInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsxgData that = (JsxgData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
