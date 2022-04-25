package uz.egov.mg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseType;
import uz.egov.mg.entity.VevxData;
import uz.egov.mg.entity.VevxInfo;
import uz.egov.mg.repository.VevxInfoRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class VevxService {
    @Autowired
    VevxInfoRepository VevxInfoRepository;

    public List<ResponseType> save(VevxInfo VevxInfo){
        List<VevxData> VevxDataList = VevxInfo.getVevxData();
        for (VevxData ls: VevxDataList) {
            ls.setInformation(VevxInfo);
        }
        List<ResponseType> responseList = new ArrayList<ResponseType>();
        VevxInfo rt = VevxInfoRepository.save(VevxInfo);
        for (VevxData VevxData : rt.getVevxData()) {
            ResponseType response = new ResponseType();
            response.setJSHSHIR(VevxData.getJshshir());
            response.setID_ORG(VevxData.getId()+"");
            response.setRECEIVE_TIME_ORG(VevxData.getInstime());
            responseList.add(response);
        }
        return  responseList;
    }

    public List<VevxInfo> readAll() {
        return VevxInfoRepository.findAll();
    }
    public List<VevxInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<VevxInfo> pagedResult = VevxInfoRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<VevxInfo>();
        }
    }
    public List<VevxInfo> findByDate(Date sana) {
        return VevxInfoRepository.findByDate(sana);
    }
    public List<VevxData> findByJSHSHIR(String jshshir) {
//        VevxData VevxData = jhInfoRepository.findByJSHSHIR(jshshir).get(0);
//        VevxInfo VevxInfo = VevxData.getInformation();
        return VevxInfoRepository.findByJSHSHIR(jshshir);
    }
}
