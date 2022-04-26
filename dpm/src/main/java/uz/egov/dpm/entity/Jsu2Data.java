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

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jsu2_data")
public class Jsu2Data extends JshshirEntity implements Serializable {

    @JsonProperty("pasn") @Column(length = 100) private String PasN; //Паспорт серияси ва рақами	String	100 тагача белги	[1]
    @JsonProperty("pasdate") @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent") private Date PasDate; //Паспорт берилган сана	Date		[1]
    @JsonProperty("passrok") @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent") private Date PasSrok; //Паспорт амал қилиш муддати	Date		[1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Jsu2Info information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Jsu2Data jsu2Data = (Jsu2Data) o;
        return getId() != null && Objects.equals(getId(), jsu2Data.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
