package uz.egov.tiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.tiv.entity.JskxData;
import uz.egov.tiv.entity.JskxInfo;
import uz.egov.tiv.repository.JskxInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JskxService {
    @Autowired
    JskxInfoRepository jskxInfoRepository;

    public List<ResponseType> save(JskxInfo jskxInfo){
        List<JskxData> jskxDataList = jskxInfo.getJSKXData();
        for (JskxData ls: jskxDataList) {
            ls.setInformation(jskxInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JskxInfo rt = jskxInfoRepository.save(jskxInfo);
        for (JskxData jskxData : rt.getJSKXData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jskxData.getJshshir());
            response.setID_ORG(jskxData.getId()+"");
            response.setRECEIVE_TIME_ORG(jskxData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JskxInfo> readAll() {
        return jskxInfoRepository.findAll();
    }
    public List<JskxInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JskxInfo> pagedResult = jskxInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JskxInfo>();
        }
    }
    public List<JskxInfo> findByDate(Date sana) {
        return jskxInfoRepository.findByDate(sana);
    }
    public List<JskxData> findByJSHSHIR(String jshshir) {
//        JskxData JskxData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JskxInfo JskxInfo = JskxData.getInformation();
        return jskxInfoRepository.findByJSHSHIR(jshshir);
    }
}
