package uz.egov.oliytalim.entity;

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
@Table(name = "jsma_diplm")
public class DData extends MainEntity implements Serializable {

    @JsonProperty("citizenship")
    @Column(length = 1)
    protected String Citizenship; //Фукаролик тури	String	1 та белги: Ўзбекистон фуқароси = 2; Хориж фуқароси = 1; Фуқаролиги бўлмаган шахс = 0.	[1]
    @JsonProperty("otm_current_id")
    protected Integer Otm_current_id; //ОТМнинг ҳозирги номи	Int	3 та белги, маълумотнома асосида	[1]
    @JsonProperty("otm_that_time_id")
    protected Integer Otm_that_time_id; //ОТМнинг диплом берилган вақтдаги номи	Int	3 та белги, маълумотнома асосида	[1]
    @JsonProperty("faculty_id")
    protected Integer Faculty_id; //Факультет номи	Int	3 та белги, маълумотнома асосида	[1]
    @JsonProperty("faculty_code")
    @Column(length = 15)
    protected String Faculty_code; //Факультет коди	String	15 та белги	[1]
    @JsonProperty("form_of_education")
    @Column(length = 2)
    protected String Form_of_education; //Таълим шакли	String	2 та белги: Кундузги = 1; Кечки = 2; Сиртқи = 3; Махсус сиртқи = 4.	[1]
    @JsonProperty("education")
    @Column(length = 1)
    protected String Education; //Таълим тури	String	1 та белги: Бакалавр = 1; Магистр = 2; Мутахассис = 3.	[1]

    @JsonProperty("education_start_datе")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date Education_start_datе; //ОТМга қабул қилинган (таълим бошланган) сана	Date 		[1]
    @JsonProperty("duration_of_edu")
    protected Integer duration_of_edu; //Таълим давомийлиги	Int	2 та рақам	[1]

    @JsonProperty("diplom_given_date")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date diplom_given_date; //ОТМни битирган (диплом берилган) сана	Date 		[1]
    @JsonProperty("otmspes")
    @Column(length = 3)
    protected String OTMspes; //Олган мутахассислиги	String	3 та белги, маълумотнома асосида	[1]
    @JsonProperty("diploma_serial")
    @Column(length = 15)
    protected String Diploma_serial; //Диплом серияси	String	15 та белги	[1]
    @JsonProperty("diploma_number")
    @Column(length = 15)
    protected String Diploma_number; //Диплом рақами	String	15 та белги	[1]
    @JsonProperty("privileged_diploma")
    protected Boolean Privileged_diploma; //Диплом тури	Boolean	Имтиёзли диплом = true; Оддий диплом = false.	[1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "data_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsmaData jsma_data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DData that = (DData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
