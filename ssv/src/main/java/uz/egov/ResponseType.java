package uz.egov;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class ResponseType {
    protected String JSHSHIR;	//ЖШШИР	String	14 та белги	[1]
    protected String ID_ORG;	//АТВ маълумотлар базасига ёзилган ID рақам	String	22 тагача белги	[0..1]
    protected String ERROR;	//Хатолик	String	255 тагача белги	[0..1]
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    protected Timestamp RECEIVE_TIME_ORG;	//АТВ базасига ёзилган вақт	Time		[0..1]

}
