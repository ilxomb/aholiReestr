package uz.egov.dpm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dpm.entity.JsxInfo;
import uz.egov.dpm.entity.JsxData;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface JsxInfoRepository extends JpaRepository<JsxInfo, UUID> {

    List<JsxInfo> findAll();

    Page<JsxInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsxInfo t WHERE date(information_date)=:sana")
    List<JsxInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsxData d WHERE d.jshshir=:jshshir")
    List<JsxData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
