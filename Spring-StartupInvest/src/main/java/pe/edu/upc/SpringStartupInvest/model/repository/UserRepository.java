package pe.edu.upc.SpringStartupInvest.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

	Optional<User> findByUsername(String username);
}
