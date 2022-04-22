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
        Integer century = 0, prm1 = 0, prm21 = 0, prm22 = 0, prm31 = 0, prm32 = 0, prm41 = 0, prm42 = 0, prm51 = 0, prm52 = 0, prm53 = 0, prm61 = 0, prm62 = 0, prm63 = 0;
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
//            try {
            poryadkobiy_nomer_grajdanina = jsbtInfoRepository.genKodGraj(
                    index_pola_i_veka_rojdeniya,
                    data_rojdeniya_ddMMyy,
                    kod_rayona_gorod);
            century = Integer.parseInt(poryadkobiy_nomer_grajdanina);
            century = century + 1;
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                //e.printStackTrace(System.err);
//                century = 1;
//            }
            kontrolnaya_ssifra = "000";

            poryadkobiy_nomer_grajdanina = century + "";
            if (poryadkobiy_nomer_grajdanina.length() < 3) {
                poryadkobiy_nomer_grajdanina =
                        kontrolnaya_ssifra.substring(poryadkobiy_nomer_grajdanina.length()) +
                                poryadkobiy_nomer_grajdanina;
            }

            kontrolnaya_ssifra = "?"; //TODO kontrolnaya_ssifra v JSHSHIR ?

            prm1 = Integer.parseInt(index_pola_i_veka_rojdeniya) * 7;

            prm21 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(0, 1)) * 3;
            prm22 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(1, 2)) * 1;

            prm31 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(2, 3)) * 7;
            prm32 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(3, 4)) * 3;

            prm41 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(4, 5)) * 1;
            prm42 = Integer.parseInt(data_rojdeniya_ddMMyy.substring(5, 6)) * 7;

            prm51 = Integer.parseInt(kod_rayona_gorod.substring(0, 1)) * 3;
            prm52 = Integer.parseInt(kod_rayona_gorod.substring(1, 2)) * 1;
            prm53 = Integer.parseInt(kod_rayona_gorod.substring(2, 3)) * 7;

            prm61 = Integer.parseInt(poryadkobiy_nomer_grajdanina.substring(0, 1)) * 3;
            prm62 = Integer.parseInt(poryadkobiy_nomer_grajdanina.substring(1, 2)) * 1;
            prm63 = Integer.parseInt(poryadkobiy_nomer_grajdanina.substring(2, 3)) * 7;

            century = prm1 + prm21 + prm22 + prm31 + prm32 + prm41 + prm42 + prm51 + prm52 + prm53 + prm61 + prm62 + prm63;
            century = century % 10;
            kontrolnaya_ssifra = century + "";

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
            response.setBFam(jsbtData.getBFam());
            response.setBIsm(jsbtData.getBIsm());
            response.setBOtch(jsbtData.getBOtch());
            response.setBData(jsbtData.getBData());
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
