package uz.egov.oak.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.oak.entity.JsidData;
import uz.egov.oak.entity.JsidInfo;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface OAKInfoRepository extends JpaRepository<JsidInfo, UUID> {

    List<JsidInfo> findAll();

    Page<JsidInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsidInfo t WHERE date(information_date)=:sana")
    List<JsidInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsidData d WHERE d.jshshir=:jshshir")
    List<JsidData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
