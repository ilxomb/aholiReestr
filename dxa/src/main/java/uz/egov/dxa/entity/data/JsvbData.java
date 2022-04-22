package uz.egov.dxa.entity.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import uz.egov.dxa.entity.JsvbInfo;
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
@Table(name = "jsvb_data")
public class JsvbData extends JshshirEntity implements Serializable {

    @JsonProperty("doc_num")
    @Column(length = 25)
    private String Doc_num; //Ўлим ҳақидаги акт ёзуви рақами	String	25 тагача белги	[1]
    @JsonProperty("doc_date")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Doc_date; //Ўлим ҳақидаги акт ёзуви санаси	Date		[1]
    @JsonProperty("branch")
    @Column(length = 4)
    private String Branch; //Ўлим ҳақидаги акт ёзувини берган ФҲДЁ бўлими	String	4 та белги, маълумотнома асосида	[1]
    @JsonProperty("death_date")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Death_date; //Ўлим санаси	Date		[1]
    @JsonProperty("death_place")
    @Column(length = 200)
    private String Death_place; //Вафот этган жойи	String	200 тагача белги	[1]
    @JsonProperty("death_reasons")
    @Column(length = 255)
    private String Death_reasons; //	Ўлим сабаби	String	255 тагача белги	[1]
    //Ўлим гувоҳномаси реквизитлари
    @JsonProperty("cert_series")
    @Column(length = 6)
    private String Cert_series; //Ўлим ҳақидаги гувоҳноманинг серияси	String	6 тагача белги	[1]
    @JsonProperty("cert_number")
    @Column(length = 12)
    private String Cert_number; //Ўлим ҳақидаги гувоҳноманинг рақами	String	12 тагача белги	[1]
    @JsonProperty("cert_date")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Cert_date; //Ўлим ҳақидаги гувоҳноманинг санаси	Date		[1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsvbInfo information;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsvbData that = (JsvbData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
