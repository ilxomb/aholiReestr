package uz.egov.dpm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dpm.entity.Jsu2Data;
import uz.egov.dpm.entity.Jsu2Info;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface Jsu2InfoRepository extends JpaRepository<Jsu2Info, UUID> {

    List<Jsu2Info> findAll();

    Page<Jsu2Info> findAll(Pageable pageable);

    @Query("SELECT t FROM Jsu2Info t WHERE date(information_date)=:sana")
    List<Jsu2Info> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM Jsu2Data d WHERE d.jshshir=:jshshir")
    List<Jsu2Data> findByJSHSHIR(@Param("jshshir") String jshshir);

}
