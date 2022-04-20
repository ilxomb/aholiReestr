package uz.egov.xalqtaim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.xalqtaim.entity.JsmaData;
import uz.egov.xalqtaim.entity.JsmaInfo;
import uz.egov.xalqtaim.repository.JEInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JEService {
    @Autowired
    JEInfoRepository jeInfoRepository;

    public List<ResponseType> save(JsmaInfo jsmaInfo){
        List<JsmaData> jsmaDataList = jsmaInfo.getJSMAData();
        for (JsmaData ls: jsmaDataList) {
            ls.setInformation(jsmaInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsmaInfo rt = jeInfoRepository.save(jsmaInfo);
        for (JsmaData JsmaData : rt.getJSMAData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(JsmaData.getJshshir());
            response.setID_ORG(JsmaData.getId()+"");
            response.setRECEIVE_TIME_ORG(JsmaData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsmaInfo> readAll() {
        return jeInfoRepository.findAll();
    }
    public List<JsmaInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsmaInfo> pagedResult = jeInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsmaInfo>();
        }
    }
    public List<JsmaInfo> findByDate(Date sana) {
        return jeInfoRepository.findByDate(sana);
    }
    public List<JsmaData> findByJSHSHIR(String jshshir) {
//        JsmaData JsmaData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsmaInfo JsmaInfo = JsmaData.getInformation();
        return jeInfoRepository.findByJSHSHIR(jshshir);
    }
}
