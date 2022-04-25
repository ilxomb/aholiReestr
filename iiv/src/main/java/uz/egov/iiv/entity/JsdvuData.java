package uz.egov.iiv.entity;

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
@Table(name = "jsdvu_data")
public class JsdvuData extends JshshirEntity implements Serializable {


    //Доимий яшаш жойи бўйича маълумотлар
    @JsonProperty("drdata")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date DRData; //Рўйхатга олинган сана	Date		[0..1]
    @JsonProperty("dydav")
    @Column(length = 3)
    protected String DYDav; //Мамлакат	String	3 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("dyvil")
    @Column(length = 2)
    protected String DYVil; //Вилоят	String	2 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("dytum")
    @Column(length = 5)
    protected String DYTum; //Шаҳар (туман)	String	5 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("dykucha")
    @Column(length = 255)
    protected String DYKucha; //Кўча номи	String	255 тагача белги	[0..1]
    @JsonProperty("dyuyn")
    @Column(length = 255)
    protected String DYUyN; //уй рақами	String	255 тагача белги	[0..1]
    @JsonProperty("dyxonan")
    @Column(length = 255)
    protected String DYxonaN; //хонадон рақами	String	255 тагача белги	[0..1]
    @JsonProperty("dykadastrn")
    @Column(length = 255)
    protected String DYKadastrN; //объектнинг кадастр рақами	String	255 тагача белги	[0..1]
    //Вақтинчалик яшаш жойи бўйича маълумотлар
    @JsonProperty("vrdata")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date VRData; //Рўйхатга олинган сана	Date		[0..1]
    @JsonProperty("vydav")
    @Column(length = 3)
    protected String VYDav; //Мамлакат	String	3 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("vyvil")
    @Column(length = 2)
    protected String VYVil; //Вилоят	String	2 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("vytum")
    @Column(length = 5)
    protected String VYTum; //Шаҳар (туман)	String	5 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("vykucha")
    @Column(length = 255)
    protected String VYKucha; //Кўча номи	String	255 тагача белги	[0..1]
    @JsonProperty("vyuyn")
    @Column(length = 255)
    protected String VYUyN; //уй рақами	String	255 тагача белги	[0..1]
    @JsonProperty("vyxonan")
    @Column(length = 255)
    protected String VYxonaN; //хонадон рақами	String	255 тагача белги	[0..1]
    @JsonProperty("vykadastrn")
    @Column(length = 255)
    protected String VYKadastrN; //объектнинг кадастр рақами	String	255 тагача белги	[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsdvuInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsdvuData that = (JsdvuData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
