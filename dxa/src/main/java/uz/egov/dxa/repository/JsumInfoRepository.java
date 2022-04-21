package uz.egov.dxa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dxa.entity.data.JsumData;
import uz.egov.dxa.entity.JsumInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JsumInfoRepository extends JpaRepository<JsumInfo, UUID> {

    List<JsumInfo> findAll();

    Page<JsumInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsumInfo t WHERE date(information_date)=:sana")
    List<JsumInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JsumInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JsumInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsumData d WHERE d.jshshir=:jshshir")
    List<JsumData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
