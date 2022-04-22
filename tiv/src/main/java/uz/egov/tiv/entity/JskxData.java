package uz.egov.tiv.entity;

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
@Table(name = "jskx_data")
public class JskxData extends JshshirEntity implements Serializable {

    @JsonProperty("ktype")
    @Column(length = 1)
    protected String KType; //Консуллик ҳисобига олиш тури	String	1 та белги, “0” – вақтинчалик, “1” – доимий.	[1]
    @JsonProperty("kmname")
    @Column(length = 255)
    protected String KMName; //Консуллик ҳисобига олинган чет эл муассасасининг номи	String	255 тагача белги	[1]
    @JsonProperty("knumber")
    @Column(length = 100)
    protected String KNumber; //Рўйхатга олиш рақами	String	100 тагача белги	[1]
    @JsonProperty("kdate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date KDate; //Рўйхатга олиш санаси	Date		[1]
    @JsonProperty("ksrok")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date KSrok; //Рўйхатдан ўтишнинг амал қилиш муддати	Date		[1]
    @JsonProperty("kdateout")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date KDateout; //Консуллик ҳисобидан чиқарилган сана	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JskxInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JskxData that = (JskxData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
