package uz.egov.ssv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.egov.ssv.entity.JSVXData;

import java.util.UUID;

public interface JXDataRepository extends JpaRepository<JSVXData, UUID> {
}
