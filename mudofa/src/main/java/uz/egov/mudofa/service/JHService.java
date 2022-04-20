package uz.egov.mudofa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.mudofa.entity.JshmData;
import uz.egov.mudofa.entity.JshmInfo;
import uz.egov.mudofa.repository.JHInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JHService {
    @Autowired
    JHInfoRepository jhInfoRepository;

    public List<ResponseType> save(JshmInfo jshmInfo){
        List<JshmData> jshmDataList = jshmInfo.getJSHMData();
        for (JshmData ls: jshmDataList) {
            ls.setInformation(jshmInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JshmInfo rt = jhInfoRepository.save(jshmInfo);
        for (JshmData JshmData : rt.getJSHMData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(JshmData.getJshshir());
            response.setID_ORG(JshmData.getId()+"");
            response.setRECEIVE_TIME_ORG(JshmData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JshmInfo> readAll() {
        return jhInfoRepository.findAll();
    }
    public List<JshmInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JshmInfo> pagedResult = jhInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JshmInfo>();
        }
    }
    public List<JshmInfo> findByDate(Date sana) {
        return jhInfoRepository.findByDate(sana);
    }
    public List<JshmData> findByJSHSHIR(String jshshir) {
//        JshmData JshmData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JshmInfo JshmInfo = JshmData.getInformation();
        return jhInfoRepository.findByJSHSHIR(jshshir);
    }
}