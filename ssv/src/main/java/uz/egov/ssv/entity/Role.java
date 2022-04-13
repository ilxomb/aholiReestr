package uz.egov.ssv.entity;

import lombok.Data;
import uz.egov.ssv.entity.enums.RoleName;

import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private RoleName name;
}
