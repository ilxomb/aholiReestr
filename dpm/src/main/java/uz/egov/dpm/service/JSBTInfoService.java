package uz.egov.dpm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.egov.dpm.ResponseType;
import uz.egov.dpm.dto.JSBTDataDto;
import uz.egov.dpm.dto.JSBTInfoDto;
import uz.egov.dpm.entity.JSBTData;
import uz.egov.dpm.entity.JSBTInformation;
import uz.egov.dpm.repository.JSBTInfoRepository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JSBTInfoService {


    @Autowired
    JSBTInfoRepository jsbtInfoRepository;

    public List<ResponseType> save(JSBTInfoDto jsbtInfoDto) {

        List<ResponseType> responseList = new ArrayList<>();

        String errors = checkInfoJsonDtoData(jsbtInfoDto);
        if(errors.equals("")){
            JSBTInformation information = jsbtInfoRepository.save(makeInfo(jsbtInfoDto));
            for (JSBTData ls : information.getJsbtData()) {
                ResponseType response = new ResponseType();
                response.setJshshir(ls.getBjshshir());
                response.setId_org(ls.getId());
                response.setReceive_time_org(ls.getInstime());
                responseList.add(response);
            }
        } else {
            ResponseType response = new ResponseType();
            response.setError(errors);
            responseList.add(response);
        }
        return responseList;
    }

    public List<JSBTInformation> findAll() {
        return jsbtInfoRepository.findAll();
    }

    public JSBTInformation findByBjshir(String jshshir) {

        if (jshshir.length() != 14){
            return new JSBTInformation();
        }

        JSBTInformation jsInfo = new JSBTInformation();
        Optional<JSBTInformation> bjshshirData = jsbtInfoRepository.findByJSHSHIR(jshshir);
        System.out.println("bshshsir =" +bjshshirData);

        if(bjshshirData.isPresent()){
            jsInfo.setInformationDate(bjshshirData.get().getInformationDate());
            jsInfo.setJsbtData(bjshshirData.get().getJsbtData());
        }
        return jsInfo;
    }

    public List<JSBTInformation> findByDate(Date sana) {
        return jsbtInfoRepository.findByDate(sana);
    }

    private JSBTInformation makeInfo(JSBTInfoDto jsbtInfoDto){
        JSBTInformation jsInfo = new JSBTInformation();
        List<JSBTData> jsbtDataList = new ArrayList<>();

        List<JSBTDataDto> jsbtDataDtoList = jsbtInfoDto.getJSBTData();
        for (JSBTDataDto jsbt: jsbtDataDtoList) {
            JSBTData jsbtData = new JSBTData();

            jsbtData.setBfam(jsbt.getBfam());
            jsbtData.setBism(jsbt.getBism());
            jsbtData.setBotch(jsbt.getBotch());
            jsbtData.setBjshshir(jsbt.getBjshshir());
            jsbtData.setBdata(Utils.convert(Utils.parseDate(jsbt.getBdata())));
            jsbtData.setInformation(jsInfo);
            jsbtDataList.add(jsbtData);
        }
        jsInfo.setJsbtData(jsbtDataList);
        jsInfo.setInformationDate(Utils.parseTimestamp(jsbtInfoDto.getInformationDate()));

        return jsInfo;
    }


    private String checkInfoJsonDtoData(JSBTInfoDto jsbtInfoDto) {
        String error_txt = "";
        String error_txt_dt = "";
        if (jsbtInfoDto.getInformationDate() == null)
            error_txt = error_txt + "Маълумот шакллантирилган вақт киритилмаган, ";
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat(Utils.time_format);
            try {
                java.util.Date d1 = dateFormat.parse(jsbtInfoDto.getInformationDate());
                if (d1.after(new java.util.Date())) {
                    error_txt = error_txt + "Маълумот шакллантирилган вақт хали етиб келмаган. (" + jsbtInfoDto.getInformationDate() + "), ";
                }
            } catch (ParseException e) {
                error_txt = error_txt + "Маълумот шакллантирилган вақт хато киритилган. " + Utils.time_format + " форматда бўлиши керак (" + jsbtInfoDto.getInformationDate() + "). " + e.getMessage() + ", ";
            }
        }
        if (jsbtInfoDto.getJSBTData() != null && jsbtInfoDto.getJSBTData().size() > 0) {
            for (JSBTDataDto jstmData : jsbtInfoDto.getJSBTData()) {
                error_txt_dt = checkDataDtoJson(jstmData);
                if (!error_txt_dt.equals("")) {
                    error_txt = error_txt + error_txt_dt;
                }
            }
        } else {
            error_txt = error_txt + "Тугилиши руйхатдан утказилган бола тугрисидаги маълумотлар киритилмаган, ";
        }

        return error_txt;
    }

    private String checkDataDtoJson(JSBTDataDto jsbtData) {
        String error_txt = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(Utils.date_format);

        if (jsbtData.getBjshshir() == null || jsbtData.getBjshshir().length() != 14)
            error_txt = error_txt + jsbtData.getBjshshir() + " ЖШШИР хато киритилган, ";
        if (jsbtData.getBfam() == null || jsbtData.getBfam().length() > 100)
            error_txt = error_txt + jsbtData.getBfam() + " Боланинг фамилияси хато киритилган, ";
        if (jsbtData.getBism() != null && jsbtData.getBism().length() > 100)
            error_txt = error_txt + jsbtData.getBism() + " Боланинг исми хато киритилган, ";
        if (jsbtData.getBotch() != null && jsbtData.getBotch().length() > 100)
            error_txt = error_txt + jsbtData.getBotch() + " Боланинг шарифи (отасининг исми) хато киритилган, ";
        if (jsbtData.getBdata() != null && !jsbtData.getBdata().toString().equals("")) {
            try {
                java.util.Date d1 = dateFormat.parse(jsbtData.getBdata().toString());
                if (d1.after(new java.util.Date())) {
                    error_txt = error_txt + jsbtData.getBdata() + " Боланинг туғилган санаси хали етиб келмаган. (" + jsbtData.getBdata() + "), ";
                }
            } catch (ParseException e) {
                error_txt = error_txt + jsbtData.getBdata() + "Боланинг туғилган санаси хато киритилган. " + Utils.date_format + " форматда бўлиши керак (" + jsbtData.getBdata() + "). " + e.getMessage() + ", ";
            }
        }
        return error_txt;
    }

}
