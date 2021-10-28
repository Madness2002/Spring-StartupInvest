package pe.edu.upc.SpringStartupInvest.service.crud.impl;



import pe.edu.upc.SpringStartupInvest.model.entity.TypeCard;
import pe.edu.upc.SpringStartupInvest.model.repository.TypeCardRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeCardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeCardServiceImpl implements TypeCardService {

	@Autowired
	private TypeCardRepository typeCardRepository;

	@Override
	public JpaRepository<TypeCard, Integer> getJpaRepository() {
		return typeCardRepository;
	}

	@Override
	public List<TypeCard> findByname(String name) throws Exception {
		
		return typeCardRepository.findByname(name);
	}
}
