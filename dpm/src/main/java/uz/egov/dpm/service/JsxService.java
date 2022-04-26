package uz.egov.dpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dpm.entity.JsxData;
import uz.egov.dpm.entity.JsxInfo;
import uz.egov.dpm.repository.JsxInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsxService {
    @Autowired
    JsxInfoRepository jsxInfoRepository;

    public List<ResponseType> save(JsxInfo jsxInfo){
        List<JsxData> jsxDataList = jsxInfo.getJsxData();
        for (JsxData ls: jsxDataList) {
            ls.setInformation(jsxInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsxInfo rt = jsxInfoRepository.save(jsxInfo);
        for (JsxData jsxData : rt.getJsxData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsxData.getJshshir());
            response.setID_ORG(jsxData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsxData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsxInfo> readAll() {
        return jsxInfoRepository.findAll();
    }
    public List<JsxInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsxInfo> pagedResult = jsxInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsxInfo>();
        }
    }
    public List<JsxInfo> findByDate(Date sana) {
        return jsxInfoRepository.findByDate(sana);
    }
    public List<JsxData> findByJSHSHIR(String jshshir) {
//        JsxData JsxData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsxInfo JsxInfo = JsxData.getInformation();
        return jsxInfoRepository.findByJSHSHIR(jshshir);
    }
}
