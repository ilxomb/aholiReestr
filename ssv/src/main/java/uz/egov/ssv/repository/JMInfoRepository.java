package uz.egov.ssv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.ssv.entity.JstmData;
import uz.egov.ssv.entity.JstmInfo;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JMInfoRepository extends JpaRepository<JstmInfo, UUID> {

    List<JstmInfo> findAll();

    Page<JstmInfo> findAll(Pageable pageable);

    @Query(value = "SELECT t FROM JstmInfo t WHERE date(InformationDate)=:sana", nativeQuery = true)
    List<JstmInfo> findByDate(@Param("sana") Date sana);

//    @Query("SELECT t FROM JstmInfo t, JstmData d WHERE t.id=d.information and d.jshshir=:jshshir")
//    List<JstmInfo> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query("SELECT d FROM JstmData d WHERE d.jshshir=:jshshir")
    List<JstmData> findByJSHSHIR(@Param("jshshir") String jshshir);

}
