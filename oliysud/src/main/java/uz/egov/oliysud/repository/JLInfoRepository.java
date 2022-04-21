package uz.egov.oliysud.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.oliysud.entity.JsmlData;
import uz.egov.oliysud.entity.JsmlInfo;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface JLInfoRepository extends JpaRepository<JsmlInfo, UUID> {

    List<JsmlInfo> findAll();

    Page<JsmlInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsmlInfo t WHERE date(information_date)=:sana")
    List<JsmlInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsmlData d WHERE d.jshshir=:jshshir")
    List<JsmlData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
