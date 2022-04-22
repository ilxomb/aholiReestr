package uz.egov.tiv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.tiv.entity.JskxData;
import uz.egov.tiv.entity.JskxInfo;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface JskxInfoRepository extends JpaRepository<JskxInfo, UUID> {

    List<JskxInfo> findAll();

    Page<JskxInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JskxInfo t WHERE date(information_date)=:sana")
    List<JskxInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JskxData d WHERE d.jshshir=:jshshir")
    List<JskxData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
