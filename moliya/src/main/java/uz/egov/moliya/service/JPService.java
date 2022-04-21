package uz.egov.moliya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.moliya.entity.JspmData;
import uz.egov.moliya.entity.JspmInfo;
import uz.egov.moliya.entity.StajALL;
import uz.egov.moliya.repository.JPInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JPService {
    @Autowired
    JPInfoRepository jpInfoRepository;

    public List<ResponseType> save(JspmInfo jspmInfo){
        List<JspmData> jspmDataList = jspmInfo.getJspmData();
        for (JspmData ls: jspmDataList) {
            ls.setInformation(jspmInfo);
            List<StajALL> stajList = ls.getStajALL();
            for (StajALL st: stajList) {
                st.setData(ls);
            }
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JspmInfo rt = jpInfoRepository.save(jspmInfo);
        for (JspmData jspmData : rt.getJspmData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jspmData.getJshshir());
            response.setID_ORG(jspmData.getId()+"");
            response.setRECEIVE_TIME_ORG(jspmData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JspmInfo> readAll() {
        return jpInfoRepository.findAll();
    }
    public List<JspmInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JspmInfo> pagedResult = jpInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JspmInfo>();
        }
    }
    public List<JspmInfo> findByDate(Date sana) {
        return jpInfoRepository.findByDate(sana);
    }
    public List<JspmData> findByJSHSHIR(String jshshir) {
//        JspmData JspmData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JspmInfo JspmInfo = JspmData.getInformation();

        return jpInfoRepository.findByJSHSHIR(jshshir);
    }
}