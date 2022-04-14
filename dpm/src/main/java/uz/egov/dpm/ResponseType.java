package uz.egov.dpm;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseType {
    protected String jshshir;	//ЖШШИР	String	14 та белги	[1]
    protected Long id_org;	//АТВ маълумотлар базасига ёзилган ID рақам	String	22 тагача белги	[0..1]
    protected String error;	//Хатолик	String	255 тагача белги	[0..1]
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    protected Timestamp receive_time_org;	//АТВ базасига ёзилган вақт	Time		[0..1]
}
