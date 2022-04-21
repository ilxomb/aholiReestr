package uz.egov.dxa.entity.data;

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
import uz.egov.dxa.entity.JsqmInfo;
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
@Table(name = "jsqm_data")
public class JsqmData extends JshshirEntity implements Serializable {

    @JsonProperty("sdate")
    @Column(length = 1)
    private String SDate; //Оилавий аҳволи	String	1 та белги, маълумотнома асосида	[1]
    //Ота оналари тўғрисидаги маълумотлар
    @JsonProperty("otashshir")
    @Column(length = 14)
    private String OTASHSHIR; //Отасининг ЖШШИР	String	14 та белги	[0..1]
    @JsonProperty("otafam")
    @Column(length = 100)
    private String OTAFam; //Отасининг фамилияси	String	100 тагача белги	[0..1]
    @JsonProperty("otaism")
    @Column(length = 100)
    private String OTAIsm; //Отасининг исми	String	100 тагача белги	[0..1]
    @JsonProperty("otadata")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date OTAData; //Отасининг туғилган санаси	Date		[0..1]
    @JsonProperty("otatjoy")
    @Column(length = 255)
    private String OTATJoy; //Отасининг туғилган жойи	String	255 тагача белги	[0..1]
    @JsonProperty("otadav")
    @Column(length = 3)
    private String OTADav; //Отасининг фуқаролиги	String	3 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("onajshshir")
    @Column(length = 14)
    private String ONAJSHSHIR; //Онасининг ЖШШИР	String	14 та белги	[0..1]
    @JsonProperty("onafam")
    @Column(length = 100)
    private String ONAFam; //Онасининг фамилияси	String	100 тагача белги	[0..1]
    @JsonProperty("onaism")
    @Column(length = 100)
    private String ONAIsm; //Онасининг исми	String	100 тагача белги	[0..1]
    @JsonProperty("onadata")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date ONAData; //Онасининг туғилган санаси	Date		[0..1]
    @JsonProperty("onatjoy")
    @Column(length = 255)
    private String ONATJoy; //Онасининг туғилган жойи	String	255 тагача белги	[0..1]
    @JsonProperty("onadav")
    @Column(length = 3)
    private String ONADav; //Онасининг фуқаролиги	String	3 та белги, маълумотнома асосида	[0..1]

    @JsonProperty("JSQMTurmush")
    @OneToMany(mappedBy = "data", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<JsqmTurmush> jsqmTurmush;

    @JsonProperty("JSQMBolalar")
    @OneToMany(mappedBy = "data", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<JsqmBolalar> jsqmBolalar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsqmInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsqmData that = (JsqmData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
