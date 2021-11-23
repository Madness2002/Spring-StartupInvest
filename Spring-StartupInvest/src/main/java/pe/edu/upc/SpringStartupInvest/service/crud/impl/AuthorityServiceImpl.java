package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import pe.edu.upc.SpringStartupInvest.model.entity.Authority;
import pe.edu.upc.SpringStartupInvest.model.entity.User;
import pe.edu.upc.SpringStartupInvest.model.repository.*;
import pe.edu.upc.SpringStartupInvest.service.crud.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public JpaRepository<Authority, Integer> getJpaRepository() {

		return authorityRepository;
	}

	@Override
	public List<Authority> findByUser(User user) {
	
		return authorityRepository.findByUser(user);
	}

}
