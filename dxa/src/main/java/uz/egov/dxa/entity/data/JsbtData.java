package uz.egov.dxa.entity.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import uz.egov.dxa.entity.JsbtInfo;
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
@Table(name = "jsbt_data")
public class JsbtData extends JshshirEntity implements Serializable {

    @JsonProperty("bfam")
    @Column(length = 100)
    private String BFam; //Боланинг фамилияси	String	100 тагача белги	[1]
    @JsonProperty("bism")
    @Column(length = 100)
    private String BIsm; //Боланинг исми	String	100 тагача белги	[1]
    @JsonProperty("botch")
    @Column(length = 100)
    private String BOtch; //Боланинг шарифи (отасининг исми)	String	100 тагача белги	[1]
    @JsonProperty("bpol")
    @Column(length = 1)
    private String BPol; //Боланинг жинси	String	1 та белги, “0” – аёл, “1” - эркак.	[1]
    @JsonProperty("bdata")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date BData; //Боланинг туғилган санаси	Date		[1]
    @JsonProperty("doc_num")
    @Column(length = 25)
    private String Doc_num; //Туғилганлик ҳакида акт ёзувининг рақами	String	25 тагача белги	[1]
    @JsonProperty("doc_date")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Doc_date; //Туғилганлик ҳакида акт ёзувининг санаси	Date		[1]
    @JsonProperty("branch")
    @Column(length = 4)
    private String Branch; //Туғилганлик ҳакида акт ёзувини берувчи бўлим	String	4 та белги, маълумотнома асосида	[1]
    @JsonProperty("num_birth")
    private Integer Num_birth; //Туғилганлар сони	Int		[1]
    @JsonProperty("btjoy")
    @Column(length = 5)
    private String BTJoy; //Боланинг туғилган жойи	String	5 та белги,  маълумотнома асосида	[1]
    @JsonProperty("bydav")
    @Column(length = 3)
    private String BYDav; //Боланинг яшаш мамлакати	String	3 та белги,  маълумотнома асосида	[1]
    @JsonProperty("byvil")
    @Column(length = 2)
    private String BYVil; //Боланинг яшаш вилояти	String	2 та белги,  маълумотнома асосида	[1]
    @JsonProperty("bytum")
    @Column(length = 5)
    private String BYTum; //Боланинг яшаш тумани (шаҳри)	String	5 та белги,  маълумотнома асосида	[1]
    @JsonProperty("byjoy")
    @Column(length = 255)
    private String BYJoy; //Боланинг яшаш жойи: кўча, уй рақами, хонадон рақами	String	255 тагача белги	[1]
    //Боланинг ота-онаси (қонуний вакиллари) тўғрисидаги маълумотлар
    @JsonProperty("mjshshir")
    @Column(length = 14)
    private String MJSHSHIR; //Онасининг ЖШШИР	String	14 та белги	[1]*
    @JsonProperty("fjshshir")
    @Column(length = 14)
    private String FJSHSHIR; //Отасининг ЖШШИР	String	14 та белги	[1]*
    //Туғилганлик ҳақидаги гувоҳноманинг реквизитлари
    @JsonProperty("cert_series")
    @Column(length = 6)
    private String Cert_series; //Туғилганлик гувоҳномаси серияси	String	6 та белги	[1]
    @JsonProperty("cert_number")
    @Column(length = 12)
    private String Cert_number; //Туғилганлик гувоҳномаси рақами	String	12 та белги	[1]
    @JsonProperty("cert_birth_date")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Cert_birth_date; //Туғилганлик гувоҳномаси санаси	Date		[1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsbtInfo information;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsbtData that = (JsbtData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
