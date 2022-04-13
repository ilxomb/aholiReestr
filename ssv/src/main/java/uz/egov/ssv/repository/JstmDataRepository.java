package uz.egov.ssv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.egov.ssv.entity.JstmData;

@Repository
public interface JstmDataRepository extends JpaRepository<JstmData, Integer> {
}