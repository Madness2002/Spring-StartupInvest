package pe.edu.upc.SpringStartupInvest.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.Authority;
import pe.edu.upc.SpringStartupInvest.model.entity.User;



@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer>{

	List<Authority> findByUser(User user);
	
}
