package uz.egov.ssv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.ssv.entity.JstmData;
import uz.egov.ssv.entity.JstmInfo;
import uz.egov.ssv.repository.JMInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JMService {
    @Autowired
    JMInfoRepository jmInfoRepository;

    public List<ResponseType> save(JstmInfo jstmInfo){
        List<JstmData> jsvxDataList = jstmInfo.getJstmData();
        for (JstmData ls: jsvxDataList) {
            ls.setInformation(jstmInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JstmInfo rt = jmInfoRepository.save(jstmInfo);
        for (JstmData JstmData : rt.getJstmData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(JstmData.getJshshir());
            response.setID_ORG(JstmData.getId()+"");
            response.setRECEIVE_TIME_ORG(JstmData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JstmInfo> readAll() {
        return jmInfoRepository.findAll();
    }
    public List<JstmInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JstmInfo> pagedResult = jmInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JstmInfo>();
        }
    }
    public List<JstmInfo> findByDate(Date sana) {
        return jmInfoRepository.findByDate(sana);
    }
    public List<JstmData> findByJSHSHIR(String jshshir) {
//        JstmData JstmData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JstmInfo JstmInfo = JstmData.getInformation();

        return jmInfoRepository.findByJSHSHIR(jshshir);
    }
}
