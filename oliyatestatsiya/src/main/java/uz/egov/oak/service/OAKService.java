package uz.egov.oak.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.oak.entity.JsidData;
import uz.egov.oak.entity.JsidInfo;
import uz.egov.oak.repository.OAKInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OAKService {
    @Autowired
    OAKInfoRepository oakInfoRepository;

    public List<ResponseType> save(JsidInfo jsidInfo){
        List<JsidData> jsmaDataList = jsidInfo.getJSIDData();
        for (JsidData ls: jsmaDataList) {
            ls.setInformation(jsidInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsidInfo rt = oakInfoRepository.save(jsidInfo);
        for (JsidData jsidData : rt.getJSIDData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsidData.getJshshir());
            response.setID_ORG(jsidData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsidData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsidInfo> readAll() {
        return oakInfoRepository.findAll();
    }
    public List<JsidInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsidInfo> pagedResult = oakInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsidInfo>();
        }
    }
    public List<JsidInfo> findByDate(Date sana) {
        return oakInfoRepository.findByDate(sana);
    }
    public List<JsidData> findByJSHSHIR(String jshshir) {
//        JsidData JsidData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsidInfo JsidInfo = JsidData.getInformation();
        return oakInfoRepository.findByJSHSHIR(jshshir);
    }
}
