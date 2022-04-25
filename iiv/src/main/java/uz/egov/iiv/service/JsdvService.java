package uz.egov.iiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.iiv.entity.JsdvData;
import uz.egov.iiv.entity.JsdvInfo;
import uz.egov.iiv.repository.JsdvInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsdvService {
    @Autowired
    JsdvInfoRepository jsdvInfoRepository;

    public List<ResponseType> save(JsdvInfo jsdvInfo){
        List<JsdvData> jsdvDataList = jsdvInfo.getJsdvData();
        for (JsdvData ls: jsdvDataList) {
            ls.setInformation(jsdvInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsdvInfo rt = jsdvInfoRepository.save(jsdvInfo);
        for (JsdvData jsdvData : rt.getJsdvData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsdvData.getJshshir());
            response.setID_ORG(jsdvData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsdvData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsdvInfo> readAll() {
        return jsdvInfoRepository.findAll();
    }
    public List<JsdvInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsdvInfo> pagedResult = jsdvInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsdvInfo>();
        }
    }
    public List<JsdvInfo> findByDate(Date sana) {
        return jsdvInfoRepository.findByDate(sana);
    }
    public List<JsdvData> findByJSHSHIR(String jshshir) {
//        JsdvData JsdvData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsdvInfo JsdvInfo = JsdvData.getInformation();
        return jsdvInfoRepository.findByJSHSHIR(jshshir);
    }
}
