package uz.egov.ssv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.egov.ssv.dto.JSTMDataDto;
import uz.egov.ssv.dto.JstmInfoDTO;
import uz.egov.ssv.entity.JstmData;
import uz.egov.ssv.entity.JstmInfo;
import uz.egov.ssv.repository.JstmDataRepository;
import uz.egov.ssv.repository.JstmInfoRepository;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SsvService {
private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
private final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Autowired
    JstmInfoRepository jstmInfoRepository;

    @Autowired
    JstmDataRepository jstmDataRepository;

    public ResponseEntity<?> save(JstmInfoDTO jstmInfoDTO){
        Set<JstmData> dataList = new HashSet<>();

        List<JSTMDataDto> dataDtoList = jstmInfoDTO.getJstmdata();
        JstmInfo info = new JstmInfo();
        info.setInformationDate(parseTimestamp(jstmInfoDTO.getInformationDate()));

        for (int i = 0; i < dataDtoList.size(); i++) {
            JstmData data = new JstmData();
            data.setReferenceNumber(dataDtoList.get(i).getReferenceNumber());
            data.setReferenceSeries(dataDtoList.get(i).getReferenceSeries());
            data.setKgroup(dataDtoList.get(i).getKgroup());
            data.setKrezus(dataDtoList.get(i).getKrezus());
            data.setJshshir(dataDtoList.get(i).getJshshir());
            data.setEkriteria_7(dataDtoList.get(i).getEkriteria_7());
            data.setEkriteria_6(dataDtoList.get(i).getEkriteria_6());
            data.setEkriteria_5(dataDtoList.get(i).getEkriteria_5());
            data.setEkriteria_4(dataDtoList.get(i).getEkriteria_4());
            data.setEkriteria_3(dataDtoList.get(i).getEkriteria_3());
            data.setEkriteria_2(dataDtoList.get(i).getEkriteria_2());
            data.setEkriteria_1(dataDtoList.get(i).getEkriteria_1());
            data.setDisabilityReason(dataDtoList.get(i).getDisabilityReason());
            data.setDisabilityPercentage(dataDtoList.get(i).getDisabilityPercentage());
            data.setDisabilityGroup(dataDtoList.get(i).getDisabilityGroup());
            data.setDisabilityDateStart(convert(parseDate(dataDtoList.get(i).getDisabilityDateStart())));
            data.setComment(dataDtoList.get(i).getComment());
            data.setDisabilityDateEnd(convert(parseDate(dataDtoList.get(i).getDisabilityDateEnd())));
            data.setEpdate(convert(parseDate(dataDtoList.get(i).getEpdate())));
//            data.setJstmInfo(info);
            dataList.add(data);
        }
        info.setJstmData(dataList);
        JstmInfo jstmInfo = jstmInfoRepository.save(info);
        return ResponseEntity.ok(jstmInfo);
    }


    public ResponseEntity<?> save1(JstmInfo jstmInfo){
        return ResponseEntity.ok(jstmInfoRepository.save(jstmInfo));
    }

    private java.util.Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private java.util.Date parseTimestamp(String timestamp) {
        try {
            return new Timestamp(DATE_TIME_FORMAT.parse(timestamp).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
    private static java.sql.Date convert(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    private static java.sql.Timestamp convertTimestamp(java.util.Date uDate) {
        java.sql.Timestamp sTimespamp = new java.sql.Timestamp(uDate.getTime());
        return sTimespamp;
    }





}
