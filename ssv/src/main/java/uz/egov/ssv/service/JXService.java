package uz.egov.ssv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.ssv.dto.ResponceResult;
import uz.egov.ssv.entity.JSVXData;
import uz.egov.ssv.entity.JSVXInformation;
import uz.egov.ssv.repository.JXDataRepository;
import uz.egov.ssv.repository.JXInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JXService {
    @Autowired
    JXInfoRepository jxInfoRepository;
//    @Autowired
//    JXDataRepository jxDataRepository;

    public List<ResponseType> save(JSVXInformation jsvxInformation){
        System.out.println(jsvxInformation);
        List<JSVXData> jsvxDataList = jsvxInformation.getJSVXData();
        for (JSVXData ls: jsvxDataList) {
            ls.setInformation(jsvxInformation);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JSVXInformation rt = jxInfoRepository.save(jsvxInformation);
        for (JSVXData jsvxData : rt.getJSVXData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsvxData.getJshshir());
            response.setID_ORG(jsvxData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsvxData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JSVXInformation> readAll() {
        return jxInfoRepository.findAll();
    }
    public List<JSVXInformation> findByDate(Date sana) {
        return jxInfoRepository.findByDate(sana);
    }
    public List<JSVXInformation> findByJSHSHIR(String jshshir) {
        return jxInfoRepository.findByJSHSHIR(jshshir);
    }
}
