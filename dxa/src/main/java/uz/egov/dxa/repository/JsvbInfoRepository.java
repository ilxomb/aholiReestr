package uz.egov.dxa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dxa.entity.data.JsvbData;
import uz.egov.dxa.entity.JsvbInfo;


import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JsvbInfoRepository extends JpaRepository<JsvbInfo, UUID> {

    List<JsvbInfo> findAll();

    Page<JsvbInfo> findAll(Pageable pageable);

    @Query("SELECT t FROM JsvbInfo t WHERE date(information_date)=:sana")
    List<JsvbInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JsvbInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JsvbInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JsvbData d WHERE d.jshshir=:jshshir")
    List<JsvbData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
