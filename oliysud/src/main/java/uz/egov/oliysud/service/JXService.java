package uz.egov.oliysud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.oliysud.entity.JsoxmData;
import uz.egov.oliysud.entity.JsoxmInfo;
import uz.egov.oliysud.repository.JXInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JXService {
    @Autowired
    JXInfoRepository jxInfoRepository;

    public List<ResponseType> save(JsoxmInfo jsoxmInfo){
        List<JsoxmData> jsmaDataList = jsoxmInfo.getJSOXMData();
        for (JsoxmData ls: jsmaDataList) {
            ls.setInformation(jsoxmInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsoxmInfo rt = jxInfoRepository.save(jsoxmInfo);
        for (JsoxmData jsoxmData : rt.getJSOXMData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsoxmData.getJshshir());
            response.setID_ORG(jsoxmData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsoxmData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsoxmInfo> readAll() {
        return jxInfoRepository.findAll();
    }
    public List<JsoxmInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsoxmInfo> pagedResult = jxInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsoxmInfo>();
        }
    }
    public List<JsoxmInfo> findByDate(Date sana) {
        return jxInfoRepository.findByDate(sana);
    }
    public List<JsoxmData> findByJSHSHIR(String jshshir) {
//        JsmaData JsmaData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsoxmInfo JsoxmInfo = JsmaData.getInformation();
        return jxInfoRepository.findByJSHSHIR(jshshir);
    }
}
