package uz.egov.oliysud.entity;

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
@Table(name = "jsml_data")
public class JsmlData extends JshshirEntity implements Serializable {

    @JsonProperty("sdate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date SDate; //Суд қарори санаси	Date		[1]
    @JsonProperty("snumber")
    @Column(length = 100)
    protected String SNumber; //Суд қарорининг рақами	String	100 тагача белги	[1]
    @JsonProperty("mlin")
    @Column(length = 1)
    protected String MLin; //Муомалага лаёқатсиз деб тан олинганлиги	String	1 та белги	[0..1]
    @JsonProperty("mlchin")
    @Column(length = 1)
    protected String MLCHin; //Муомала лаёқати чекланган деб тан олинганлиги	String	1 та белги	[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsmlInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsmlData that = (JsmlData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
