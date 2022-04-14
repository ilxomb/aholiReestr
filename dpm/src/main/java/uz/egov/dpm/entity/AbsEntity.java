package uz.egov.dpm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class AbsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @CreatedBy
    @JsonIgnore
    private Integer createdBy;

    @CreationTimestamp
    @JsonIgnore
    private Timestamp instime;

    @UpdateTimestamp
    @JsonIgnore
    private Timestamp updtime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AbsEntity absEntity = (AbsEntity) o;
        return id != null && Objects.equals(id, absEntity.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
