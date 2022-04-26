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
@Table(name = "jsu4_data")
public class Jsu4Data extends JshshirEntity implements Serializable {

    @JsonProperty("pasn") @Column(length = 100) private String PasN; //Ҳужжат серияси ва рақами	String	100 тагача белги	[1]
    @JsonProperty("pasdate") @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent") private Date PasDate; //Ҳужжат берилган сана	Date		[1]
    @JsonProperty("passrok") @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent") private Date PasSrok; //Ҳужжат амал қилиш муддати	Date		[1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Jsu4Info information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Jsu4Data jsu4Data = (Jsu4Data) o;
        return getId() != null && Objects.equals(getId(), jsu4Data.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
