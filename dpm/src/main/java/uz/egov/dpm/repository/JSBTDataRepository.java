package uz.egov.dpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.egov.dpm.entity.JSBTData;

import java.util.List;
import java.util.Optional;

@Repository
public interface JSBTDataRepository extends JpaRepository<JSBTData, Long> {
    Optional<List<JSBTData>> findByBjshshir(String jshir);

    List<JSBTData> findByInformation_Id(Long id);

}
