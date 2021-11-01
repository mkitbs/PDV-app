package rs.mkgroup.pdvapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.mkgroup.pdvapp.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@EntityGraph(value = "User.Roles.Permissions")
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String username);

	@Query("select u from User u where u.id = ?#{principal.id}")
	Optional<User> findCurrentUser();
	
	@Query("select u from User u where u.id != ?#{principal.id}")
	List<User> findUsersExceptSelf();

}
