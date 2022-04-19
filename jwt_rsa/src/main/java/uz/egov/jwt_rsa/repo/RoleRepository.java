package uz.egov.jwt_rsa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.egov.jwt_rsa.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
