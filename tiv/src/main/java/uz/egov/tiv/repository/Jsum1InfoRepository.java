package uz.egov.tiv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.tiv.entity.Jsum1Data;
import uz.egov.tiv.entity.Jsum1Info;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface Jsum1InfoRepository extends JpaRepository<Jsum1Info, UUID> {

    List<Jsum1Info> findAll();

    Page<Jsum1Info> findAll(Pageable pageable);

    @Query("SELECT t FROM Jsum1Info t WHERE date(information_date)=:sana")
    List<Jsum1Info> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JshmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM Jsum1Data d WHERE d.jshshir=:jshshir")
    List<Jsum1Data> findByJSHSHIR(@Param("jshshir") String jshshir);

}
