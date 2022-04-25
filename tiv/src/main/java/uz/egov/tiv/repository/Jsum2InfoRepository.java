package uz.egov.tiv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.tiv.entity.Jsum2Data;
import uz.egov.tiv.entity.Jsum2Info;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface Jsum2InfoRepository extends JpaRepository<Jsum2Info, UUID> {

    List<Jsum2Info> findAll();

    Page<Jsum2Info> findAll(Pageable pageable);

    @Query("SELECT t FROM Jsum2Info t WHERE date(information_date)=:sana")
    List<Jsum2Info> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM Jsum2Data d WHERE d.jshshir=:jshshir")
    List<Jsum2Data> findByJSHSHIR(@Param("jshshir") String jshshir);

}
