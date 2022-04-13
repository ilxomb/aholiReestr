package uz.egov.ssv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.egov.ssv.entity.JstmInfo;

@Repository
public interface JstmInfoRepository extends JpaRepository<JstmInfo, Long> {

}
