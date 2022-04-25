package uz.egov.iiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.iiv.entity.JsxgData;
import uz.egov.iiv.entity.JsxgInfo;
import uz.egov.iiv.entity.JsxgToifa;
import uz.egov.iiv.repository.JsxgInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsxgService {
    @Autowired
    JsxgInfoRepository jsxgInfoRepository;

    public List<ResponseType> save(JsxgInfo jsxgInfo){
        List<JsxgData> jsxgDataList = jsxgInfo.getJsxgData();
        for (JsxgData ls: jsxgDataList) {
            ls.setInformation(jsxgInfo);
            List<JsxgToifa> jsxgToifas = ls.getJsxgToifa();
            for (JsxgToifa toifa: jsxgToifas) {
                toifa.setData(ls);
            }
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsxgInfo rt = jsxgInfoRepository.save(jsxgInfo);
        for (JsxgData jsxgData : rt.getJsxgData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsxgData.getJshshir());
            response.setID_ORG(jsxgData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsxgData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsxgInfo> readAll() {
        return jsxgInfoRepository.findAll();
    }
    public List<JsxgInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsxgInfo> pagedResult = jsxgInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsxgInfo>();
        }
    }
    public List<JsxgInfo> findByDate(Date sana) {
        return jsxgInfoRepository.findByDate(sana);
    }
    public List<JsxgData> findByJSHSHIR(String jshshir) {
//        JsxgData JsxgData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsxgInfo JsxgInfo = JsxgData.getInformation();
        return jsxgInfoRepository.findByJSHSHIR(jshshir);
    }
}
