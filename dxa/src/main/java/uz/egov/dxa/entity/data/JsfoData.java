package uz.egov.dxa.entity.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import uz.egov.dxa.entity.JsfoInfo;
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
@Table(name = "jsbt_data")
public class JsfoData extends JshshirEntity implements Serializable {

    //JSHSHIR	ЖШШИР	String	14 та белги	[1]
    //Фарзандликка олувчи тўғрисидаги маълумотлар
    @JsonProperty("fojshshir")
    @Column(length = 14)
    private String FOJSHSHIR; //ЖШШИР	String	14 та белги	[1]
    @JsonProperty("fofam")
    @Column(length = 100)
    private String FOFam; //Фамилияси	String	100 тагача белги	[1]
    @JsonProperty("foism")
    @Column(length = 100)
    private String FOIsm; //Исми	String	100 тагача белги	[1]
    @JsonProperty("footch")
    @Column(length = 100)
    private String FOOtch; //Отасининг исми	String	100 тагача белги	[0..1]
    @JsonProperty("fodate")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date FODate; //Туғилган санаси	Date		[1]
    @JsonProperty("fojoy")
    @Column(length = 255)
    private String FOJoy; //Туғилган жойи	String	255 тагача белги	[1]
    @JsonProperty("fodav")
    @Column(length = 3)
    private String FODav; //Фуқаролиги	String	3 та белги, маълумотнома асосида	[1]
    @JsonProperty("fodatein")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date FODatein; //Фарзандликка олишнинг белгиланган санаси	Date		[1]
    @JsonProperty("fodateout")
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date FODateout; //Бекор қилинган санаси	Date		[0..1]

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private JsfoInfo information;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JsfoData that = (JsfoData) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
