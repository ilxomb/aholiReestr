package uz.egov.ssv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.egov.ssv.entity.JSVXInformation;

import java.util.UUID;

public interface JXInfoRepository extends JpaRepository<JSVXInformation, UUID> {
}
