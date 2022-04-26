package uz.egov.dpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dpm.entity.Jsu3Data;
import uz.egov.dpm.entity.Jsu3Info;
import uz.egov.dpm.repository.Jsu3InfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Jsu3Service {
    @Autowired
    Jsu3InfoRepository jsu3InfoRepository;

    public List<ResponseType> save(Jsu3Info jsu3Info){
        List<Jsu3Data> jsu3DataList = jsu3Info.getJsu3Data();
        for (Jsu3Data ls: jsu3DataList) {
            ls.setInformation(jsu3Info);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        Jsu3Info rt = jsu3InfoRepository.save(jsu3Info);
        for (Jsu3Data jsu3Data : rt.getJsu3Data()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsu3Data.getJshshir());
            response.setID_ORG(jsu3Data.getId()+"");
            response.setRECEIVE_TIME_ORG(jsu3Data.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<Jsu3Info> readAll() {
        return jsu3InfoRepository.findAll();
    }
    public List<Jsu3Info> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<Jsu3Info> pagedResult = jsu3InfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Jsu3Info>();
        }
    }
    public List<Jsu3Info> findByDate(Date sana) {
        return jsu3InfoRepository.findByDate(sana);
    }
    public List<Jsu3Data> findByJSHSHIR(String jshshir) {
//        Jsu3Data Jsu3Data = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        Jsu3Info Jsu3Info = Jsu3Data.getInformation();
        return jsu3InfoRepository.findByJSHSHIR(jshshir);
    }
}
