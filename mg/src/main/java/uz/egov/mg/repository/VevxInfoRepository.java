package uz.egov.mg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.mg.entity.VevxData;
import uz.egov.mg.entity.VevxInfo;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface VevxInfoRepository extends JpaRepository<VevxInfo, UUID> {

    List<VevxInfo> findAll();

    Page<VevxInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM VevxInfo t WHERE date(information_date)=:sana")
    List<VevxInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM VevxData d WHERE d.jshshir=:jshshir")
    List<VevxData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
