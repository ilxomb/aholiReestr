package uz.egov.ssv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.egov.ssv.entity.JSVXInformation;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public interface JXInfoRepository extends JpaRepository<JSVXInformation, UUID> {

    List<JSVXInformation> findAll();

    Page<JSVXInformation> findAll(Pageable pageable);

    @Query(value = "SELECT t FROM JSVXInformation t WHERE date(InformationDate)=:sana", nativeQuery = true)
    List<JSVXInformation> findByDate(@Param("sana") Date sana);

    @Query("SELECT t FROM JSVXInformation t, JSVXData d WHERE t.id=d.information and d.jshshir=:jshshir")
    List<JSVXInformation> findByJSHSHIR(@Param("jshshir") String jshshir);

}
