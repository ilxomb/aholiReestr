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
@Table(name = "jsqm_turmush")
public class JsqmTurmush extends MainEntity implements Serializable {


    @JsonProperty("tujshshir")
    @Column(length = 14)
    private String TUJSHSHIR; //Турмуш ўртоғининг ЖШШИР	String	14 та белги	[1]
    @JsonProperty("tufam_old")
    @Column(length = 100)
    private String TUFam_old; //Турмуш ўртоғининг никоҳдан олдинги фамилияси	String	100 тагача белги	[1]
    @JsonProperty("tufam_after")
    @Column(length = 100)
    private String TUFam_after; //Турмуш ўртоғининг никоҳдан кейин берилган фамилияси	String	100 тагача белги	[1]
    @JsonProperty("tuism")
    @Column(length = 100)
    private String TUIsm; //Турмуш ўртоғининг исми	String	100 тагача белги	[1]
    @JsonProperty("tuotch")
    @Column(length = 100)
    private String TUOtch; //Турмуш ўртоғининг отасининг исми	String	100 тагача белги	[0..1]
    @JsonProperty("tudata")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date TUData; //Турмуш ўртоғининг туғилган санаси	Date		[1]
    @JsonProperty("tutjoy")
    @Column(length = 255)
    private String TUTJoy; //Турмуш ўртоғининг туғилган жойи	String	255 тагача белги	[1]
    @JsonProperty("tudav")
    @Column(length = 3)
    private String TUDav; //Турмуш ўртоғининг фуқаролиги	String	3 та белги, маълумотнома асосида	[1]

    //Никоҳ тузилганлиги ҳақидаги гувоҳноманинг реквизитлари
    @JsonProperty("doc_numn")
    @Column(length = 25)
    private String Doc_numN; //Акт ёзуви рақами	String	25 тагача белги	[0..1]
    @JsonProperty("doc_daten")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Doc_dateN; //Акт ёзуви санаси	Date		[0..1]
    @JsonProperty("branchn")
    @Column(length = 4)
    private String BranchN; //ФҲДЁ бўлими идентификатори	String	4 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("cert_seriesn")
    @Column(length = 6)
    private String Cert_seriesN; //Никоҳ гувоҳномаси серияси	String	6 та белги	[0..1]
    @JsonProperty("cert_numbern")
    @Column(length = 12)
    private String Cert_numberN; //Никоҳ гувоҳномаси рақами	String	12 та белги	[0..1]
    @JsonProperty("cert_daten")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Cert_dateN; //Никоҳ гувоҳномаси санаси	Date		[0..1]

    //Никоҳдан ажралиш тўғрисидаги гувоҳноманинг реквизитлари
    @JsonProperty("doc_numa")
    @Column(length = 25)
    private String Doc_numA; //Акт ёзуви рақами	String	25 тагача белги	[0..1]
    @JsonProperty("doc_datea")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Doc_dateA; //Акт ёзуви санаси	Date		[0..1]
    @JsonProperty("fam_olda")
    @Column(length = 100)
    private String Fam_oldA; //Турмуш ўртоғининг фамилияси	String	100 тагача белги	[1]
    @JsonProperty("fam_aftera")
    @Column(length = 100)
    private String Fam_afterA; //Турмуш ўртоғининг ажрашгандан сўнг берилган фамилияси	String	100 тагача белги	[1]
    @JsonProperty("brancha")
    @Column(length = 7)
    private String BranchA; //ФҲДЁ бўлими идентификатори	String	4 та белги, маълумотнома асосида	[0..1]
    @JsonProperty("cert_seriesa")
    @Column(length = 6)
    private String Cert_seriesA; //Ажралиш ҳақидаги гувоҳнома серияси	String	6 та белги	[0..1]
    @JsonProperty("cert_numbera")
    @Column(length = 12)
    private String Cert_numberA; //Ажралиш ҳақидаги гувоҳнома рақами	String	12 та белги	[0..1]
    @JsonProperty("cert_datea")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date Cert_dateA; //Ажралиш ҳақидаги гувоҳнома санаси	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsqmData data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsqmTurmush that = (JsqmTurmush) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}