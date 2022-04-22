package uz.egov;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Setter
@Getter
public class ResponseJSBT {
    @JsonProperty("bfam")
    protected String BFam; //Боланинг фамилияси	String	100 тагача белги	[1]
    @JsonProperty("bism")
    protected String BIsm; //Боланинг исми.	String	100 тагача белги	[1]
    @JsonProperty("botch")
    protected String BOtch; //Боланинг шарифи (отасининг исми)	String	100 тагача белги	[1]
    @JsonProperty("bdata")
    @JsonFormat(pattern = "dd.MM.yyyy", timezone = "Asia/Tashkent")
    protected Date BData; //Боланинг туғилган санаси	Date		[1]
    @JsonProperty("bjshshir")
    protected String BJSHSHIR; //Болага берилган ЖШШИР	String	14 та белги	[1]
    @JsonProperty("id_org")
    protected String ID_ORG; //АТВ маълумотлар базасига ёзилган ID рақам	String	22 тагача белги	[0..1]
    @JsonProperty("error")
    protected String ERROR; //Хатолик	String	255 тагача белги	[0..1]
    @JsonProperty("receive_time_org")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    protected Timestamp RECEIVE_TIME_ORG; //АТВ базасига ёзилган вақт	Time		[0..1]
}
