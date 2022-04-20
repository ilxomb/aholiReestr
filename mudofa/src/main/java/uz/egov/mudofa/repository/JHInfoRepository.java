package uz.egov.mudofa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.mudofa.entity.JshmData;
import uz.egov.mudofa.entity.JshmInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface JHInfoRepository extends JpaRepository<JshmInfo, UUID> {

    List<JshmInfo> findAll();

    Page<JshmInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JshmInfo t WHERE date(information_date)=:sana")
    List<JshmInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JshmData d WHERE d.jshshir=:jshshir")
    List<JshmData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
