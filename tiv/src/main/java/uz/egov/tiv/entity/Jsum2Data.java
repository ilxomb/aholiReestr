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
@Table(name = "jsum2_data")
public class Jsum2Data extends JshshirEntity implements Serializable {


    @JsonProperty("jfamnew")
    @Column(length = 100)
    protected String JFamnew; //Ўзгартирилган фамилияси	String	100 тагача белги	[0..1]
    @JsonProperty("jismnew")
    @Column(length = 100)
    protected String JIsmnew; //Ўзгартирилган исми	String	100 тагача белги	[0..1]
    @JsonProperty("jotchnew")
    @Column(length = 100)
    protected String JOtchnew; //Ўзгартирилган отасининг исми	String	100 тагача белги	[0..1]
    @JsonProperty("jdatenew")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date JDatenew; //Ўзгартирилган туғилган санаси	Date		[0..1]
    @JsonProperty("jmilnew")
    @Column(length = 3)
    protected String JMilnew; //Ўзгартирилган миллати	String	3 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("jdavnew")
    @Column(length = 3)
    protected String JDavnew; //Ўзгартирилган фуқаролиги	String	3 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("jpolnew")
    @Column(length = 1)
    protected String JPolnew; //Ўзгартирилган жинси	String	1 та белги, “0” – аёл, “1” – эркак.	[0..1]

    //Шахсга оид маълумотларга ўзгартиришлар киритиш учун асос бўлган ҳужжат реквизитлари

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Jsum2Info information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Jsum2Data that = (Jsum2Data) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
