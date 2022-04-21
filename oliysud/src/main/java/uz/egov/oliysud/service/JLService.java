package uz.egov.oliysud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.oliysud.entity.JsmlData;
import uz.egov.oliysud.entity.JsmlInfo;
import uz.egov.oliysud.repository.JLInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JLService {
    @Autowired
    JLInfoRepository jlInfoRepository;

    public List<ResponseType> save(JsmlInfo jsmlInfo){
        List<JsmlData> jsmlDataList = jsmlInfo.getJSMLData();
        for (JsmlData ls: jsmlDataList) {
            ls.setInformation(jsmlInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsmlInfo rt = jlInfoRepository.save(jsmlInfo);
        for (JsmlData jsmlData : rt.getJSMLData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsmlData.getJshshir());
            response.setID_ORG(jsmlData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsmlData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsmlInfo> readAll() {
        return jlInfoRepository.findAll();
    }
    public List<JsmlInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsmlInfo> pagedResult = jlInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsmlInfo>();
        }
    }
    public List<JsmlInfo> findByDate(Date sana) {
        return jlInfoRepository.findByDate(sana);
    }
    public List<JsmlData> findByJSHSHIR(String jshshir) {
//        JsmaData JsmaData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsmlInfo JsmlInfo = JsmaData.getInformation();
        return jlInfoRepository.findByJSHSHIR(jshshir);
    }
}
