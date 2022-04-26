package uz.egov.dpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.dpm.entity.Jsu4Data;
import uz.egov.dpm.entity.Jsu4Info;
import uz.egov.dpm.repository.Jsu4InfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Jsu4Service {
    @Autowired
    Jsu4InfoRepository jsu4InfoRepository;

    public List<ResponseType> save(Jsu4Info jsu4Info){
        List<Jsu4Data> jsu4DataList = jsu4Info.getJsu4Data();
        for (Jsu4Data ls: jsu4DataList) {
            ls.setInformation(jsu4Info);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        Jsu4Info rt = jsu4InfoRepository.save(jsu4Info);
        for (Jsu4Data jsu4Data : rt.getJsu4Data()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsu4Data.getJshshir());
            response.setID_ORG(jsu4Data.getId()+"");
            response.setRECEIVE_TIME_ORG(jsu4Data.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<Jsu4Info> readAll() {
        return jsu4InfoRepository.findAll();
    }
    public List<Jsu4Info> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<Jsu4Info> pagedResult = jsu4InfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Jsu4Info>();
        }
    }
    public List<Jsu4Info> findByDate(Date sana) {
        return jsu4InfoRepository.findByDate(sana);
    }
    public List<Jsu4Data> findByJSHSHIR(String jshshir) {
//        Jsu4Data Jsu4Data = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        Jsu4Info Jsu4Info = Jsu4Data.getInformation();
        return jsu4InfoRepository.findByJSHSHIR(jshshir);
    }
}
