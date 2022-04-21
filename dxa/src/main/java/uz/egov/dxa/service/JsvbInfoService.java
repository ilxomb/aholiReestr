package uz.egov.dxa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dxa.entity.JsvbInfo;
import uz.egov.dxa.entity.data.JsvbData;
import uz.egov.dxa.repository.JsvbInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsvbInfoService {
    @Autowired
    JsvbInfoRepository jsvbInfoRepository;

    public List<ResponseType> save(JsvbInfo jsvbInfo){
        List<JsvbData> jsvbDataList = jsvbInfo.getJsvbData();
        for (JsvbData ls: jsvbDataList) {
            ls.setInformation(jsvbInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        JsvbInfo rt = jsvbInfoRepository.save(jsvbInfo);
        for (JsvbData jsvbData : rt.getJsvbData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsvbData.getJshshir());
            response.setID_ORG(jsvbData.getId()+"");
            response.setRECEIVE_TIME_ORG(jsvbData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<JsvbInfo> readAll() {
        return jsvbInfoRepository.findAll();
    }
    public List<JsvbInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsvbInfo> pagedResult = jsvbInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsvbInfo>();
        }
    }
    public List<JsvbInfo> findByDate(Date sana) {
        return jsvbInfoRepository.findByDate(sana);
    }
    public List<JsvbData> findByJSHSHIR(String jshshir) {
//        JsvbData JsvbData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsvbInfo JsvbInfo = JsvbData.getInformation();

        return jsvbInfoRepository.findByJSHSHIR(jshshir);
    }
}
