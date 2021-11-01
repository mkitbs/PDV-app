package rs.mkgroup.pdvapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.Role;
import rs.mkgroup.pdvapp.model.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(RoleName name);
	
	boolean existsByName(RoleName name);
	
}
