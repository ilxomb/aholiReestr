package uz.egov.ssv.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import uz.egov.entity.JshshirEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jstm_data")
public class JstmData extends JshshirEntity implements Serializable {

    @JsonProperty("kgroup")
    @Column(length = 2)
    private String kgroup;    //Қон гуруҳи	String	2 та белги, маълумотнома асосида	[1]

    @JsonProperty("krezus")
    @Column(length = 2)
    private String krezus;    //Қандай резусга мансублиги		2 та белги, маълумотнома асосида	[0..1]

    @JsonProperty("disabilitygroup")
    private Integer disabilityGroup;    //Ногиронлик гуруҳи	Int	1 талик рақамдан иборат	[0..1]

    @JsonProperty("disabilitypercentage")
    private Integer disabilityPercentage;    //Ногиронлик даражаси	Int		[0..1]

    @JsonProperty("disabilityreason")
    @Column(length = 2)
    private String disabilityReason;    //Ногиронлик сабаби	String	2 та белги, маълумотнома асосида	[0..1]

    @JsonProperty("disabilitydatestart")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date disabilityDateStart;    //Шахсни ногирон деб тан олинган сана	Date 		[0..1]

    @JsonProperty("disabilitydateend")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date disabilityDateEnd;    //Ногиронлик тугаш санаси	Date		[0..1]

    @JsonProperty("referenceseries")
    @Column(length = 10)
    private String referenceSeries;    //Маълумотнома серияси	String	10 тагача белги	[0..1]

    @JsonProperty("referencenumber")
    @Column(length = 8)
    private String referenceNumber;    //Маълумотнома рақами	String	8 тагача белги	[0..1]

    @JsonProperty("ekriteria_1")
    private Integer ekriteria_1;    //Ўзига ўзи хизмат кўрсатиш лаёқати	Int		[0..1]

    @JsonProperty("ekriteria_2")
    private Integer ekriteria_2;    //Мустақил харакатланиш лаёқати	Int		[0..1]

    @JsonProperty("ekriteria_3")
    private Integer ekriteria_3;    //Мўлжал олиш лаёқати	Int		[0..1]

    @JsonProperty("ekriteria_4")
    private Integer ekriteria_4;    //Мулоқот қилиш лаёқати	Int		[0..1]

    @JsonProperty("ekriteria_5")
    private Integer ekriteria_5;    //Ўз хулқ-атворини назорат қилиш лаёқати	Int		[0..1]

    @JsonProperty("ekriteria_6")
    private Integer ekriteria_6;    //Ўқиш, билимларни ўзлаштириш ва такрорлаш, малака ва кўникмаларни эгаллаб олиш лаёқати	Int		[0..1]

    @JsonProperty("ekriteria_7")
    private Integer ekriteria_7;    //Меҳнат фаолияти билан шуғулланиш лаёқати	Int		[0..1]

    @JsonProperty("epdate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    private Date epdate;    //Расмийлаштирилган сана (агар меҳнатда майибланганлик фоизи бўлса, протокол расмийлаштирилган сана)	Date		[0..1]

    @JsonProperty("comment")
    @Column(length = 1000, columnDefinition = "text")
    private String comment;    //Изоҳ (қўшимча маълумотлар)	String	1000 тагача белги	[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JstmInfo information;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JstmData JstmData = (JstmData) o;
        return getId() != null && Objects.equals(getId(), JstmData.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
