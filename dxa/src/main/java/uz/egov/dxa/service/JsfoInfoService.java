package uz.egov.dxa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dxa.entity.JsfoInfo;
import uz.egov.dxa.entity.data.JsfoData;
import uz.egov.dxa.repository.JsfoInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsfoInfoService {
    @Autowired
    JsfoInfoRepository jsfoInfoRepository;

    public List<ResponseType> save(JsfoInfo jsfoInfo){
        List<JsfoData> jsfoDataList = jsfoInfo.getJsfoData();
        for (JsfoData ls: jsfoDataList) {
            ls.setInformation(jsfoInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsfoInfo rt = jsfoInfoRepository.save(jsfoInfo);
        for (JsfoData jsfoData : rt.getJsfoData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsfoData.getJshshir());
            response.setID_ORG(jsfoData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsfoData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsfoInfo> readAll() {
        return jsfoInfoRepository.findAll();
    }
    public List<JsfoInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsfoInfo> pagedResult = jsfoInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsfoInfo>();
        }
    }
    public List<JsfoInfo> findByDate(Date sana) {
        return jsfoInfoRepository.findByDate(sana);
    }
    public List<JsfoData> findByJSHSHIR(String jshshir) {
//        JsfoData JsfoData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsfoInfo JsfoInfo = JsfoData.getInformation();

        return jsfoInfoRepository.findByJSHSHIR(jshshir);
    }
}
