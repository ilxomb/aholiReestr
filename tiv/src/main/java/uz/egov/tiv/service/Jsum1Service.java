package uz.egov.tiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.tiv.entity.Jsum1Data;
import uz.egov.tiv.entity.Jsum1Info;
import uz.egov.tiv.repository.Jsum1InfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Jsum1Service {
    @Autowired
    Jsum1InfoRepository jsum1InfoRepository;

    public List<ResponseType> save(Jsum1Info jsum1Info){
        List<Jsum1Data> jsum1DataList = jsum1Info.getJsum1Data();
        for (Jsum1Data ls: jsum1DataList) {
            ls.setInformation(jsum1Info);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        Jsum1Info rt = jsum1InfoRepository.save(jsum1Info);
        for (Jsum1Data jsum1Data : rt.getJsum1Data()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsum1Data.getJshshir());
            response.setID_ORG(jsum1Data.getId()+"");
            response.setRECEIVE_TIME_ORG(jsum1Data.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<Jsum1Info> readAll() {
        return jsum1InfoRepository.findAll();
    }
    public List<Jsum1Info> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<Jsum1Info> pagedResult = jsum1InfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Jsum1Info>();
        }
    }
    public List<Jsum1Info> findByDate(Date sana) {
        return jsum1InfoRepository.findByDate(sana);
    }
    public List<Jsum1Data> findByJSHSHIR(String jshshir) {
//        Jsum1Data Jsum1Data = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        Jsum1Info Jsum1Info = Jsum1Data.getInformation();
        return jsum1InfoRepository.findByJSHSHIR(jshshir);
    }
}
