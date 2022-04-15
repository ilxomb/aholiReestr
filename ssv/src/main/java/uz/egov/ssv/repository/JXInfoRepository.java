package uz.egov.ssv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.ssv.entity.JSVXData;
import uz.egov.ssv.entity.JSVXInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JXInfoRepository extends JpaRepository<JSVXInfo, UUID> {

    List<JSVXInfo> findAll();

    Page<JSVXInfo> findAll(Pageable pageable);

    @Query(value = "SELECT t FROM JSVXInfo t WHERE date(InformationDate)=:sana", nativeQuery = true)
    List<JSVXInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JSVXInfo t, JSVXData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JSVXInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JSVXData d WHERE d.jshshir=:jshshir")
    List<JSVXData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
