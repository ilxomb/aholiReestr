package uz.egov.dxa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dxa.entity.JsfoInfo;
import uz.egov.dxa.entity.data.JsfoData;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JsfoInfoRepository extends JpaRepository<JsfoInfo, UUID> {

    List<JsfoInfo> findAll();

    Page<JsfoInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsfoInfo t WHERE date(information_date)=:sana")
    List<JsfoInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JsfoInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JsfoInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsfoData d WHERE d.jshshir=:jshshir")
    List<JsfoData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
