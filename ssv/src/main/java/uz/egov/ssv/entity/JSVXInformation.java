package uz.egov.ssv.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "jsvx_info")
public class JSVXInformation extends AbsEntity implements Serializable {

    @JsonProperty("information_date")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= "dd.MM.yyyy HH:mm:ss")
    private Date InformationDate;

    @JsonProperty("jsvxdata")
    @OneToMany(mappedBy = "information", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private List<JSVXData> JSVXData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JSVXInformation that = (JSVXInformation) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
