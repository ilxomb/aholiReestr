package uz.egov.ssv.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class JstmData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 14, nullable = false)
    private String jshshir;    //ЖШШИР	String	14 та белги	[1]

    @Column(length = 2)
    private String kgroup;    //Қон гуруҳи	String	2 та белги, маълумотнома асосида	[1]

    @Column(length = 2)
    private String krezus;    //Қандай резусга мансублиги		2 та белги, маълумотнома асосида	[0..1]

    private Integer disabilityGroup;    //Ногиронлик гуруҳи	Int	1 талик рақамдан иборат	[0..1]

    private Integer disabilityPercentage;    //Ногиронлик даражаси	Int		[0..1]

    private String disabilityReason;    //Ногиронлик сабаби	String	2 та белги, маълумотнома асосида	[0..1]

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date disabilityDateStart;    //Шахсни ногирон деб тан олинган сана	Date 		[0..1]

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date disabilityDateEnd;    //Ногиронлик тугаш санаси	Date		[0..1]

    @Column(length = 10)
    private String referenceSeries;    //Маълумотнома серияси	String	10 тагача белги	[0..1]

    private String referenceNumber;    //Маълумотнома рақами	String	8 тагача белги	[0..1]

    private Integer ekriteria_1;    //Ўзига ўзи хизмат кўрсатиш лаёқати	Int		[0..1]

    private Integer ekriteria_2;    //Мустақил харакатланиш лаёқати	Int		[0..1]

    private Integer ekriteria_3;    //Мўлжал олиш лаёқати	Int		[0..1]

    private Integer ekriteria_4;    //Мулоқот қилиш лаёқати	Int		[0..1]

    private Integer ekriteria_5;    //Ўз хулқ-атворини назорат қилиш лаёқати	Int		[0..1]

    private Integer ekriteria_6;    //Ўқиш, билимларни ўзлаштириш ва такрорлаш, малака ва кўникмаларни эгаллаб олиш лаёқати	Int		[0..1]

    private Integer ekriteria_7;    //Меҳнат фаолияти билан шуғулланиш лаёқати	Int		[0..1]

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date epdate;    //Расмийлаштирилган сана (агар меҳнатда майибланганлик фоизи бўлса, протокол расмийлаштирилган сана)	Date		[0..1]

    @Column(columnDefinition = "text")
    private String comment;    //Изоҳ (қўшимча маълумотлар)	String	1000 тагача белги	[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    private JstmInfo jstmInfo;

    @CreationTimestamp
    private Timestamp instime;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JstmData jstmData = (JstmData) o;
        return id != null && Objects.equals(id, jstmData.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
