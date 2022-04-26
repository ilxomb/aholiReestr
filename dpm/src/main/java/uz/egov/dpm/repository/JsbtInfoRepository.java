package uz.egov.dpm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dpm.entity.JsbtData;
import uz.egov.dpm.entity.JsbtInfo;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface JsbtInfoRepository extends JpaRepository<JsbtInfo, UUID> {

    List<JsbtInfo> findAll();

    Page<JsbtInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsbtInfo t WHERE date(information_date)=:sana")
    List<JsbtInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsbtData d WHERE d.bjshshir=:jshshir")
    List<JsbtData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
