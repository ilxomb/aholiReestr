package uz.egov.dpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.dpm.entity.JSBTInformation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface JSBTInfoRepository extends JpaRepository<JSBTInformation, Long> {

    @Query(value = "SELECT * FROM jsbt_info t, jsbt_data d WHERE t.id=d.info_id and d.bjshshir=:jshshir", nativeQuery = true)
    Optional<JSBTInformation> findByJSHSHIR(@Param("jshshir") String jshshir);

//    @Query(value = "select t from JSBTInformation t, JSBTData d where t.id=d.information.id and d.bjshshir=:jshshir")
//    Optional<JSBTInformation> findByJSHSHIR(@Param("jshshir") String jshshir);

    @Query(value = "SELECT * FROM JSBTInformation t WHERE date(informationDate)=:sana", nativeQuery = true)
    List<JSBTInformation> findByDate(@Param("sana") Date sana);

}
