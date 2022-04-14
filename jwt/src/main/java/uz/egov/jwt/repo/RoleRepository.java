package uz.egov.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.egov.jwt.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
