package uz.egov.dxa.entity.data;

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
@Table(name = "jsqm_bolalar")
public class JsqmBolalar extends MainEntity implements Serializable {

    @JsonProperty("bjshshir")
    @Column(length = 14)
    private String BJSHSHIR; //Боласининг ЖШШИР	String	14 та белги	[1]
    @JsonProperty("bfam")
    @Column(length = 100)
    private String BFam; //Боласининг фамилияси	String	100 тагача белги	[1]
    @JsonProperty("bism")
    @Column(length = 100)
    private String BIsm; //Боласининг исми	String	100 тагача белги	[1]
    @JsonProperty("botch")
    @Column(length = 100)
    private String BOtch; //Боласининг шарифи (отасининг исми)	String	100 тагача белги	[0..1]
    @JsonProperty("bpol")
    @Column(length = 1)
    private String BPol; //Боланинг жинси	String	1 та белги, “0” – аёл, “1” - эркак.	[1]
    @JsonProperty("bdata")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date BData; //Боласининг туғилган санаси	Date		[1]
    @JsonProperty("btjoy")
    @Column(length = 255)
    private String BTJoy; //Боласининг туғилган жойи	String	255 тагача белги	[1]
    @JsonProperty("bydav")
    @Column(length = 3)
    private String BYDav; //Боласининг фуқаролиги (агар қабул қилинган бўлса)	String	3 та белги, маълумотнома асосида	[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsqmData data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsqmBolalar that = (JsqmBolalar) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}