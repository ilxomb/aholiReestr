package uz.egov.iiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.iiv.entity.JsdvuData;
import uz.egov.iiv.entity.JsdvuInfo;
import uz.egov.iiv.repository.JsdvuInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsdvuService {
    @Autowired
    JsdvuInfoRepository jsdvuInfoRepository;

    public List<ResponseType> save(JsdvuInfo jsdvuInfo){
        List<JsdvuData> jsdvuDataList = jsdvuInfo.getJsdvuData();
        for (JsdvuData ls: jsdvuDataList) {
            ls.setInformation(jsdvuInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsdvuInfo rt = jsdvuInfoRepository.save(jsdvuInfo);
        for (JsdvuData jsdvuData : rt.getJsdvuData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsdvuData.getJshshir());
            response.setID_ORG(jsdvuData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsdvuData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsdvuInfo> readAll() {
        return jsdvuInfoRepository.findAll();
    }
    public List<JsdvuInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsdvuInfo> pagedResult = jsdvuInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsdvuInfo>();
        }
    }
    public List<JsdvuInfo> findByDate(Date sana) {
        return jsdvuInfoRepository.findByDate(sana);
    }
    public List<JsdvuData> findByJSHSHIR(String jshshir) {
//        JsdvuData JsdvuData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsdvuInfo JsdvuInfo = JsdvuData.getInformation();
        return jsdvuInfoRepository.findByJSHSHIR(jshshir);
    }
}
