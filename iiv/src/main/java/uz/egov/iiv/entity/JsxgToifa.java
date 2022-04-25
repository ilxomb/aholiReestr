package uz.egov.iiv.entity;

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
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jsxg_toifa")
public class JsxgToifa extends MainEntity implements Serializable {

    @JsonProperty("pcategory")
    @Column(length = 2)
    private String pCategory; //Тоифа тури	String	2 та белги, маълумотнома асосида 	[1]
    @JsonProperty("pbegin")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date pBegin; //Тоифага руҳсат берилган сана	Date		[1]
    @JsonProperty("pend")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date pEnd; //Тоифага руҳсат берилган муддат	Date		[1]
    @JsonProperty("pcomment")
    @Column(length = 100)
    private String pComment; //Тоифа бўйича қўшимча маълумотлар	String	100 тагача белги	[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsxgData data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsxgToifa that = (JsxgToifa) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}