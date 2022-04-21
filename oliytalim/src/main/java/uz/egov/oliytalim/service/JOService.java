package uz.egov.oliytalim.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.oliytalim.entity.DData;
import uz.egov.oliytalim.entity.JsmaData;
import uz.egov.oliytalim.entity.JsmaInfo;
import uz.egov.oliytalim.repository.JOInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JOService {
    @Autowired
    JOInfoRepository joInfoRepository;

    public List<ResponseType> save(JsmaInfo jsmaInfo){
        List<JsmaData> jsmaDataList = jsmaInfo.getJSMAData();
        for (JsmaData ls: jsmaDataList) {
            ls.setInformation(jsmaInfo);
            List<DData> dDataList = ls.getDdata();
            for (DData dd: dDataList) {
                dd.setJsma_data(ls);
            }
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsmaInfo rt = joInfoRepository.save(jsmaInfo);
        for (JsmaData jsmaData : rt.getJSMAData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsmaData.getJshshir());
            response.setID_ORG(jsmaData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsmaData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsmaInfo> readAll() {
        return joInfoRepository.findAll();
    }
    public List<JsmaInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsmaInfo> pagedResult = joInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsmaInfo>();
        }
    }
    public List<JsmaInfo> findByDate(Date sana) {
        return joInfoRepository.findByDate(sana);
    }
    public List<JsmaData> findByJSHSHIR(String jshshir) {
//        JsmaData JsmaData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsmaInfo JsmaInfo = JsmaData.getInformation();
        return joInfoRepository.findByJSHSHIR(jshshir);
    }
}
