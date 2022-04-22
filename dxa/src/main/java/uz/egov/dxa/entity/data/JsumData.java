package uz.egov.dxa.entity.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import uz.egov.dxa.entity.JsumInfo;
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
@Table(name = "jsum_data")
public class JsumData extends JshshirEntity implements Serializable {

    @JsonProperty("jfamnew")
    @Column(length = 100)
    private String JFamnew; //Ўзгартирилган фамилияси	String	100 тагача белги	[0..1]
    @JsonProperty("jismnew")
    @Column(length = 100)
    private String JIsmnew; //Ўзгартирилган исми	String	100 тагача белги	[0..1]
    @JsonProperty("jotchnew")
    @Column(length = 100)
    private String JOtchnew; //Ўзгартирилган отасининг исми	String	100 тагача белги	[0..1]
    @JsonProperty("jdatenew")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date JDatenew; //Ўзгартирилган туғилган санаси	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsumInfo information;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsumData that = (JsumData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
