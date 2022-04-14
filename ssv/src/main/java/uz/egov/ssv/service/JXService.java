package uz.egov.ssv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.ssv.entity.JSVXData;
import uz.egov.ssv.entity.JSVXInfo;
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

    public List<ResponseType> save(JSVXInfo jsvxInformation){
        System.out.println(jsvxInformation);
        List<JSVXData> jsvxDataList = jsvxInformation.getJSVXData();
        for (JSVXData ls: jsvxDataList) {
            ls.setInformation(jsvxInformation);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JSVXInfo rt = jxInfoRepository.save(jsvxInformation);
        for (JSVXData jsvxData : rt.getJSVXData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsvxData.getJshshir());
            response.setID_ORG(jsvxData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsvxData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JSVXInfo> readAll() {
        return jxInfoRepository.findAll();
    }
    public List<JSVXInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JSVXInfo> pagedResult = jxInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JSVXInfo>();
        }
    }
    public List<JSVXInfo> findByDate(Date sana) {
        return jxInfoRepository.findByDate(sana);
    }
    public List<JSVXInfo> findByJSHSHIR(String jshshir) {
        return jxInfoRepository.findByJSHSHIR(jshshir);
    }
}
