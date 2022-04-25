package uz.egov.iiv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.iiv.entity.JsxgData;
import uz.egov.iiv.entity.JsxgInfo;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface JsxgInfoRepository extends JpaRepository<JsxgInfo, UUID> {

    List<JsxgInfo> findAll();

    Page<JsxgInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsxgInfo t WHERE date(information_date)=:sana")
    List<JsxgInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsxgData d WHERE d.jshshir=:jshshir")
    List<JsxgData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
