package uz.egov.dxa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseJSBT;
import uz.egov.dxa.entity.JsbtInfo;
import uz.egov.dxa.entity.data.JsbtData;
import uz.egov.dxa.repository.JsbtInfoRepository;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsbtInfoService {
    @Autowired
    JsbtInfoRepository jsbtInfoRepository;

    public List<ResponseJSBT> save(JsbtInfo jsbtInfo){
        List<JsbtData> jsbtDataList = jsbtInfo.getJsbtData();
        for (JsbtData ls: jsbtDataList) {
            ls.setInformation(jsbtInfo);
        }
        List<ResponseJSBT> responseList = new ArrayList<ResponseJSBT>();
        JsbtInfo rt = jsbtInfoRepository.save(jsbtInfo);
        for (JsbtData jsbtData : rt.getJsbtData()) {
            ResponseJSBT response = new ResponseJSBT();
            response.setBJSHSHIR(jsbtData.getJshshir());
            response.setID_ORG(jsbtData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsbtData.getInstime());
            responseList.add(response);
        }
        return responseList;
    }

    public List<JsbtInfo> readAll() {
        return jsbtInfoRepository.findAll();
    }
    public List<JsbtInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsbtInfo> pagedResult = jsbtInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsbtInfo>();
        }
    }
    public List<JsbtInfo> findByDate(Date sana) {
        return jsbtInfoRepository.findByDate(sana);
    }
    public List<JsbtData> findByJSHSHIR(String jshshir) {
//        JsbtData JsbtData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsbtInfo JsbtInfo = JsbtData.getInformation();

        return jsbtInfoRepository.findByJSHSHIR(jshshir);
    }
}
