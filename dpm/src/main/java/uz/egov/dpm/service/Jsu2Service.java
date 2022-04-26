package uz.egov.dpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dpm.entity.Jsu2Data;
import uz.egov.dpm.entity.Jsu2Info;
import uz.egov.dpm.repository.Jsu2InfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Jsu2Service {
    @Autowired
    Jsu2InfoRepository jsu2InfoRepository;

    public List<ResponseType> save(Jsu2Info jsu2Info){
        List<Jsu2Data> jsu2DataList = jsu2Info.getJsu2Data();
        for (Jsu2Data ls: jsu2DataList) {
            ls.setInformation(jsu2Info);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        Jsu2Info rt = jsu2InfoRepository.save(jsu2Info);
        for (Jsu2Data jsu2Data : rt.getJsu2Data()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsu2Data.getJshshir());
            response.setID_ORG(jsu2Data.getId()+"");
            response.setRECEIVE_TIME_ORG(jsu2Data.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<Jsu2Info> readAll() {
        return jsu2InfoRepository.findAll();
    }
    public List<Jsu2Info> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<Jsu2Info> pagedResult = jsu2InfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Jsu2Info>();
        }
    }
    public List<Jsu2Info> findByDate(Date sana) {
        return jsu2InfoRepository.findByDate(sana);
    }
    public List<Jsu2Data> findByJSHSHIR(String jshshir) {
//        Jsu2Data Jsu2Data = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        Jsu2Info Jsu2Info = Jsu2Data.getInformation();
        return jsu2InfoRepository.findByJSHSHIR(jshshir);
    }
}
