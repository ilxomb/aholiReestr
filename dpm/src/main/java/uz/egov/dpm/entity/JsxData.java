package uz.egov.dpm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import uz.egov.entity.JshshirEntity;
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
@Table(name = "jsx_data")
public class JsxData extends JshshirEntity implements Serializable {


    @JsonProperty("jfoto") @Column(length = 100) private String JFoto; //Рақамли сурат (ўн олти ёшдан ошган шахслар учун)	String	Base64 форматидаги файл	[0..1]
    @JsonProperty("jbizi") @Column(length = 100) private String JBizi; //Бармоқ излари (ўн олти ёшдан ошган шахслар учун)	String	Base64 форматидаги файл	[0..1]
    @JsonProperty("jdav") @Column(length = 3) private String JDav; //Фуқаролиги	String	3 та белги, маълумотнома асосида	[1]
    @JsonProperty("jxasos") @Column(length = 255) private String JXAsos; //Ўзбекистон Республикаси фуқаролигини олиш ёки тугатиш асоси	String	255 тагача белги	[1]
    @JsonProperty("jxdate") @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent") private Date JXDate; //Ўзбекистон Республикаси фуқаролигини олиш ёки тугатиш санаси	Date		[1]

    //Расмийлаштирилган шахсни тасдиқловчи ҳужжатнинг реквизитлари		...	...

    // Субъектнинг ЖШШИР аввал мавжуд бўлмаганда тақдим этиладиган маълумотлар
    @JsonProperty("jfam") @Column(length = 100) private String JFam; //Фамилияси	String	100 тагача белги	[0..1]
    @JsonProperty("jism") @Column(length = 100) private String JIsm; //Исми	String	100 тагача белги	[0..1]
    @JsonProperty("jotch") @Column(length = 100) private String JOtch; //Отасининг исми	String	100 тагача белги	[0..1]
    @JsonProperty("jpol") @Column(length = 1) private String JPol; //Жинси	String	1 та белги, “0” – аёл, “1” - эркак.	[0..1]
    @JsonProperty("jbdate") @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent") private Date JBDate; //Туғилган санаси	Date		[0..1]
    @JsonProperty("jtjoy") @Column(length = 5) private String JTJoy; //Туғилган жойи	String	5 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("jmil") @Column(length = 3) private String JMil; //Миллати	String	3 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("jkod") @Column(length = 255) private String JKod; //Фуқаролиги давлат томонидан берилган шахсий идентификация коди (чет эл фуқароси учун)	String	255 тагача белги	[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsxInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsxData jsxData = (JsxData) o;
        return getId() != null && Objects.equals(getId(), jsxData.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
