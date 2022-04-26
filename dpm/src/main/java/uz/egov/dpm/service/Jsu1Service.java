package uz.egov.dpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dpm.entity.Jsu1Data;
import uz.egov.dpm.entity.Jsu1Info;
import uz.egov.dpm.repository.Jsu1InfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Jsu1Service {
    @Autowired
    Jsu1InfoRepository jsu1InfoRepository;

    public List<ResponseType> save(Jsu1Info jsu1Info){
        List<Jsu1Data> jsu1DataList = jsu1Info.getJsu1Data();
        for (Jsu1Data ls: jsu1DataList) {
            ls.setInformation(jsu1Info);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        Jsu1Info rt = jsu1InfoRepository.save(jsu1Info);
        for (Jsu1Data jsu1Data : rt.getJsu1Data()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsu1Data.getJshshir());
            response.setID_ORG(jsu1Data.getId()+"");
            response.setRECEIVE_TIME_ORG(jsu1Data.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<Jsu1Info> readAll() {
        return jsu1InfoRepository.findAll();
    }
    public List<Jsu1Info> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<Jsu1Info> pagedResult = jsu1InfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Jsu1Info>();
        }
    }
    public List<Jsu1Info> findByDate(Date sana) {
        return jsu1InfoRepository.findByDate(sana);
    }
    public List<Jsu1Data> findByJSHSHIR(String jshshir) {
//        Jsu1Data Jsu1Data = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        Jsu1Info Jsu1Info = Jsu1Data.getInformation();
        return jsu1InfoRepository.findByJSHSHIR(jshshir);
    }
}
