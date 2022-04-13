package uz.egov.ssv.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class ResponceResult {
    private String JSHSHIR;

    private String ID_ORG;

    private String ERROR;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date RECEIVE_TIME_ORG;
}
