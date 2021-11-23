package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import pe.edu.upc.SpringStartupInvest.model.repository.UserRepository;
import pe.edu.upc.SpringStartupInvest.model.entity.User;
import pe.edu.upc.SpringStartupInvest.service.crud.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public JpaRepository<User, Integer> getJpaRepository() {

		return userRepository;
	}

	@Override
	public Optional<User> findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
