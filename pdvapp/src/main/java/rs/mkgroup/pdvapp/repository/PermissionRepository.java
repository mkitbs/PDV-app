package rs.mkgroup.pdvapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.Permission;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	Permission findByName(String name);

	boolean existsByName(String name);

}
