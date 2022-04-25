package uz.egov.tiv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.tiv.entity.Jsum2Data;
import uz.egov.tiv.entity.Jsum2Info;
import uz.egov.tiv.repository.Jsum2InfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class Jsum2Service {
    @Autowired
    Jsum2InfoRepository jsum2InfoRepository;

    public List<ResponseType> save(Jsum2Info jsum2Info){
        List<Jsum2Data> jsum2DataList = jsum2Info.getJsum2Data();
        for (Jsum2Data ls: jsum2DataList) {
            ls.setInformation(jsum2Info);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        Jsum2Info rt = jsum2InfoRepository.save(jsum2Info);
        for (Jsum2Data jsum2Data : rt.getJsum2Data()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(jsum2Data.getJshshir());
            response.setID_ORG(jsum2Data.getId()+"");
            response.setRECEIVE_TIME_ORG(jsum2Data.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<Jsum2Info> readAll() {
        return jsum2InfoRepository.findAll();
    }
    public List<Jsum2Info> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<Jsum2Info> pagedResult = jsum2InfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Jsum2Info>();
        }
    }
    public List<Jsum2Info> findByDate(Date sana) {
        return jsum2InfoRepository.findByDate(sana);
    }
    public List<Jsum2Data> findByJSHSHIR(String jshshir) {
//        Jsum2Data Jsum2Data = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        Jsum2Info Jsum2Info = Jsum2Data.getInformation();
        return jsum2InfoRepository.findByJSHSHIR(jshshir);
    }
}
