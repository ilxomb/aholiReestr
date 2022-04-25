package uz.egov.mg.entity;

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
@Table(name = "vevx_data")
public class VevxData extends JshshirEntity implements Serializable {

    @JsonProperty("vxjshshir")
    @Column(length = 14)
    protected String VXJSHSHIR; //Васийнинг ёки ҳомийнинг ЖШШИР	String	14 та белги	[1]
    @JsonProperty("vxfio")
    @Column(length = 255)
    protected String VXFIO; //Васийнинг ёки ҳомийнинг фамилияси, исми, отасининг исми	String	255 тагача белги	[1]
    @JsonProperty("vxbdate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date VXBDate; //Васийнинг ёки ҳомийнинг туғилган санаси	Date 		[1]
    @JsonProperty("vxjoy")
    @Column(length = 255)
    protected String VXJoy; //Васийнинг ёки ҳомийнинг туғилган жойи	String	255 тагача белги	[1]
    @JsonProperty("vxgraj")
    @Column(length = 3)
    protected String VXgraj; //Васийнинг ёки ҳомийнинг фуқаролиги	String	3 та белги, маълумотнома асосида	[1]
    @JsonProperty("vxdate")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date VXDate; //Васийлик, ҳомийлик белгиланган сана	Date		[1]
    @JsonProperty("vxsrok")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date VXSrok; //Васийлик, ҳомийлик тугатилган сана	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private VevxInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VevxData that = (VevxData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
