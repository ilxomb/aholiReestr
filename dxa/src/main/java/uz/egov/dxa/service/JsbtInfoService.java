package uz.egov.dxa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.egov.ResponseJSBT;
import uz.egov.dxa.entity.JsbtInfo;
import uz.egov.dxa.entity.data.JsbtData;
import uz.egov.dxa.repository.JsbtInfoRepository;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class JsbtInfoService {
    @Autowired
    JsbtInfoRepository jsbtInfoRepository;

    public List<ResponseJSBT> save(JsbtInfo jsbtInfo) {
        List<JsbtData> jsbtDataList = jsbtInfo.getJsbtData();

        String index_pola_i_veka_rojdeniya = "";
        String data_rojdeniya_ddMMyy = "";
        String kod_rayona_gorod = "";
        String poryadkobiy_nomer_grajdanina = "";
        String kontrolnaya_ssifra = "";
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
        Calendar calendar = Calendar.getInstance();
        int century = 0;
        for (JsbtData ls : jsbtDataList) {
            //BPol	Боланинг жинси	String	1 та белги, “0” – аёл, “1” - эркак.	[1]
            index_pola_i_veka_rojdeniya = ls.getBPol();
            calendar.setTime(ls.getBData());
            century = (calendar.get(Calendar.YEAR) / 100);
            if (century == 18 && index_pola_i_veka_rojdeniya.equals("1")) {
                index_pola_i_veka_rojdeniya = "1";
            } else if (century == 18 && index_pola_i_veka_rojdeniya.equals("0")) {
                index_pola_i_veka_rojdeniya = "2";
            } else if (century == 19 && index_pola_i_veka_rojdeniya.equals("1")) {
                index_pola_i_veka_rojdeniya = "3";
            } else if (century == 19 && index_pola_i_veka_rojdeniya.equals("0")) {
                index_pola_i_veka_rojdeniya = "4";
            } else if (century == 20 && index_pola_i_veka_rojdeniya.equals("1")) {
                index_pola_i_veka_rojdeniya = "5";
            } else if (century == 20 && index_pola_i_veka_rojdeniya.equals("0")) {
                index_pola_i_veka_rojdeniya = "6";
            } else
                index_pola_i_veka_rojdeniya = "?";
            data_rojdeniya_ddMMyy = sdf.format(ls.getBData());
            kod_rayona_gorod = ls.getBYTum().substring(0, 3);
            try {
                poryadkobiy_nomer_grajdanina = jsbtInfoRepository.genKodGraj(
                        index_pola_i_veka_rojdeniya,
                        data_rojdeniya_ddMMyy,
                        kod_rayona_gorod);
                century = Integer.getInteger(poryadkobiy_nomer_grajdanina);
                century = century + 1;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                century = 1;
            }
            poryadkobiy_nomer_grajdanina = century + "";
            kontrolnaya_ssifra = "000";
            poryadkobiy_nomer_grajdanina =
                    poryadkobiy_nomer_grajdanina.substring(3 - poryadkobiy_nomer_grajdanina.length()) +
                            poryadkobiy_nomer_grajdanina;


            kontrolnaya_ssifra = "?"; //TODO kontrolnaya_ssifra v JSHSHIR ?

            century = 0
             + Integer.parseInt(kontrolnaya_ssifra) * 7
             + Integer.parseInt(data_rojdeniya_ddMMyy.substring(0, 2)) * 31
             + Integer.parseInt(data_rojdeniya_ddMMyy.substring(2, 4)) * 73
             + Integer.parseInt(data_rojdeniya_ddMMyy.substring(4, 6)) * 17
             + Integer.parseInt(kod_rayona_gorod) * 317
             + Integer.parseInt(poryadkobiy_nomer_grajdanina) * 317
            ;
            //010180
            //012345
            //123456

            ls.setJshshir(index_pola_i_veka_rojdeniya +
                    data_rojdeniya_ddMMyy +
                    kod_rayona_gorod +
                    poryadkobiy_nomer_grajdanina +
                    kontrolnaya_ssifra
            );
            ls.setInformation(jsbtInfo);
        }
        List<ResponseJSBT> responseList = new ArrayList<ResponseJSBT>();
        JsbtInfo rt = jsbtInfoRepository.save(jsbtInfo);
        for (JsbtData jsbtData : rt.getJsbtData()) {
            ResponseJSBT response = new ResponseJSBT();
            response.setBJSHSHIR(jsbtData.getJshshir());
            response.setID_ORG(jsbtData.getId() + "");
            response.setRECEIVE_TIME_ORG(jsbtData.getInstime());
            responseList.add(response);
        }
        return responseList;
    }

    public List<JsbtInfo> readAll() {
        return jsbtInfoRepository.findAll();
    }

    public List<JsbtInfo> readAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("instime").descending());
        Page<JsbtInfo> pagedResult = jsbtInfoRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<JsbtInfo>();
        }
    }

    public List<JsbtInfo> findByDate(Date sana) {
        return jsbtInfoRepository.findByDate(sana);
    }

    public List<JsbtData> findByJSHSHIR(String jshshir) {
//        JsbtData JsbtData = jxInfoRepository.findByJSHSHIR(jshshir).get(0);
//        JsbtInfo JsbtInfo = JsbtData.getInformation();

        return jsbtInfoRepository.findByJSHSHIR(jshshir);
    }
}
