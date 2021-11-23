package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.Optional;

import pe.edu.upc.SpringStartupInvest.model.entity.User;

public interface UserService extends CrudService<User, Integer> {
	Optional<User> findByUsername(String username);
}
