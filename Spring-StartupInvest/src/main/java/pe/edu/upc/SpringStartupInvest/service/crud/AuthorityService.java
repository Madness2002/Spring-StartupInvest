package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.SpringStartupInvest.model.entity.Authority;
import pe.edu.upc.SpringStartupInvest.model.entity.User;


public interface AuthorityService extends CrudService<Authority, Integer> {

	List<Authority> findByUser(User user);
	
}
