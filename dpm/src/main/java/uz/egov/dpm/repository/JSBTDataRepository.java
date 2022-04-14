package uz.egov.dpm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.egov.dpm.entity.JSBTData;

@Repository
public interface JSBTDataRepository extends JpaRepository<JSBTData, Long> {

}
