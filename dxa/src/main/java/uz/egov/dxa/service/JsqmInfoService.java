package uz.egov.dxa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dxa.entity.JsqmInfo;
import uz.egov.dxa.entity.data.JsqmBolalar;
import uz.egov.dxa.entity.data.JsqmData;
import uz.egov.dxa.entity.data.JsqmTurmush;
import uz.egov.dxa.repository.JsqmInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsqmInfoService {
    @Autowired
    JsqmInfoRepository jsqmInfoRepository;

    public List<ResponseType> save(JsqmInfo jsqmInfo){
        List<JsqmData> jsqmDataList = jsqmInfo.getJsqmData();
        for (JsqmData ls: jsqmDataList) {
            ls.setInformation(jsqmInfo);
            List<JsqmTurmush> jsqmTurmushList = ls.getJsqmTurmush();
            for (JsqmTurmush tr: jsqmTurmushList) {
                tr.setData(ls);
            }
            List<JsqmBolalar> jsqmBolalarList = ls.getJsqmBolalar();
            for (JsqmBolalar bl: jsqmBolalarList) {
                bl.setData(ls);
            }
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsqmInfo rt = jsqmInfoRepository.save(jsqmInfo);
        for (JsqmData jsqmData : rt.getJsqmData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsqmData.getJshshir());
            response.setID_ORG(jsqmData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsqmData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsqmInfo> readAll() {
        return jsqmInfoRepository.findAll();
    }
    public List<JsqmInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsqmInfo> pagedResult = jsqmInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsqmInfo>();
        }
    }
    public List<JsqmInfo> findByDate(Date sana) {
        return jsqmInfoRepository.findByDate(sana);
    }
    public List<JsqmData> findByJSHSHIR(String jshshir) {
//        JsqmData JsqmData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsqmInfo JsqmInfo = JsqmData.getInformation();
        return jsqmInfoRepository.findByJSHSHIR(jshshir);
    }
}
