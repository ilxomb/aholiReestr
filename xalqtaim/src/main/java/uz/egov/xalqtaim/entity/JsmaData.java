package uz.egov.xalqtaim.entity;

import com.fasterxml.jackson.annotation.*;
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
@Table(name = "jsma_data")
public class JsmaData extends JshshirEntity implements Serializable {

    @JsonProperty("mname")
    protected String MName; //Умумий таълим муассасасининг номи	String	255 тагача белги	[1]
    @JsonProperty("mnumber")
    protected Integer MNumber; //Умумий таълим муассасасининг рақами	Int		[1]
    @JsonProperty("min")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Min; //Умумий таълим муассасасига қабул қилинган сана	Date 		[1]
    @JsonProperty("mout")
    @JsonFormat(pattern = "dd.MM.yyyy")
    protected Date Mout; //Умумий таълим муассасасини битирган (чиқарилган) сана	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsmaInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsmaData that = (JsmaData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
