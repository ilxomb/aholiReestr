package uz.egov.dxa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dxa.entity.data.JsqmData;
import uz.egov.dxa.entity.JsqmInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JsqmInfoRepository extends JpaRepository<JsqmInfo, UUID> {

    List<JsqmInfo> findAll();

    Page<JsqmInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsqmInfo t WHERE date(information_date)=:sana")
    List<JsqmInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JsqmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JsqmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsqmData d WHERE d.jshshir=:jshshir")
    List<JsqmData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
