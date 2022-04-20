package uz.egov.oliytalim.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.oliytalim.entity.JsmaData;
import uz.egov.oliytalim.entity.JsmaInfo;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface JOInfoRepository extends JpaRepository<JsmaInfo, UUID> {

    List<JsmaInfo> findAll();

    Page<JsmaInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsmaInfo t WHERE date(information_date)=:sana")
    List<JsmaInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsmaData d WHERE d.jshshir=:jshshir")
    List<JsmaData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
