package uz.egov.jwt_rsa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.egov.jwt_rsa.model.CertEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertRepository extends JpaRepository<CertEntity, String> {

    @Query(value = "SELECT * FROM mh.cert_files t order by t.instime desc", nativeQuery = true)
    List<CertEntity> findTopByInstime();

}