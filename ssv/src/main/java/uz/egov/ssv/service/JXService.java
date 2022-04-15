package uz.egov.ssv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.ssv.entity.JsvxData;
import uz.egov.ssv.entity.JsvxInfo;
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

    public List<ResponseType> save(JsvxInfo jsvxInformation){
        System.out.println(jsvxInformation);
        List<JsvxData> jsvxDataList = jsvxInformation.getJSVXData();
        for (JsvxData ls: jsvxDataList) {
            ls.setInformation(jsvxInformation);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsvxInfo rt = jxInfoRepository.save(jsvxInformation);
        for (JsvxData jsvxData : rt.getJSVXData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsvxData.getJshshir());
            response.setID_ORG(jsvxData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsvxData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsvxInfo> readAll() {
        return jxInfoRepository.findAll();
    }
    public List<JsvxInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsvxInfo> pagedResult = jxInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsvxInfo>();
        }
    }
    public List<JsvxInfo> findByDate(Date sana) {
        return jxInfoRepository.findByDate(sana);
    }
    public List<JsvxData> findByJSHSHIR(String jshshir) {
//        JSVXData jsvxData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JSVXInfo jsvxInfo = jsvxData.getInformation();

        return jxInfoRepository.findByJSHSHIR(jshshir);
    }
}
