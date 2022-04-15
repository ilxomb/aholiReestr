package uz.egov.moliya.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.egov.moliya.entity.JspmData;
import uz.egov.moliya.entity.JspmInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface JPInfoRepository  extends JpaRepository<JspmInfo, UUID> {

    List<JspmInfo> findAll();

    Page<JspmInfo> findAll(Pageable pageable);

    @Query(value = "SELECT t FROM JspmInfo t WHERE date(Information_Date)=:sana", nativeQuery = true)
    List<JspmInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JspmInfo t, JspmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JspmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JspmData d WHERE d.jshshir=:jshshir")
    List<JspmData> findByJSHSHIR(@Param("jshshir") String jshshir);
}

