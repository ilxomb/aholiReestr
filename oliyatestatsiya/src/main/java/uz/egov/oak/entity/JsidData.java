package uz.egov.oak.entity;

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
@Table(name = "jsid_data")
public class JsidData extends JshshirEntity implements Serializable {

    @JsonProperty("idaraja")
    @Column(length = 2)
    protected String Idaraja; //	Илмий даража	String	2 та белги, маълумотнома асосида	[1]
    @JsonProperty("unvon")
    @Column(length = 2)
    protected String Unvon; //	Унвон тури	Date 	2 та белги, маълумотнома асосида	[1]
    @JsonProperty("iin")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Iin; //	Берилган сана	Date		[1]
    @JsonProperty("iout")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Iout; //	Маҳрум этилган сана	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsidInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsidData that = (JsidData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
