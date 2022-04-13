package uz.egov.ssv.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.egov.ssv.dto.ResponceResult;
import uz.egov.ssv.entity.JSVXData;
import uz.egov.ssv.entity.JSVXInformation;
import uz.egov.ssv.repository.JXDataRepository;
import uz.egov.ssv.repository.JXInfoRepository;

import java.util.List;

@Service
public class JXService {
    @Autowired
    JXInfoRepository jxInfoRepository;
    @Autowired
    JXDataRepository jxDataRepository;

    public String save(JSVXInformation jsvxInformation){
        System.out.println(jsvxInformation);
        List<JSVXData> jsvxDataList = jsvxInformation.getJSVXData();

        for (JSVXData ls: jsvxDataList) {
            ls.setJsvxInformation(jsvxInformation);
        }

        jxInfoRepository.save(jsvxInformation);
        return  "saqla";
    }

}
