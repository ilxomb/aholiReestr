package uz.egov.dpm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dpm.entity.Jsu1Data;
import uz.egov.dpm.entity.Jsu1Info;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface Jsu1InfoRepository extends JpaRepository<Jsu1Info, UUID> {

    List<Jsu1Info> findAll();

    Page<Jsu1Info> findAll(Pageable pageable);

    @Query("SELECT t FROM Jsu1Info t WHERE date(information_date)=:sana")
    List<Jsu1Info> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM Jsu1Data d WHERE d.jshshir=:jshshir")
    List<Jsu1Data> findByJSHSHIR(@Param("jshshir") String jshshir);

}
