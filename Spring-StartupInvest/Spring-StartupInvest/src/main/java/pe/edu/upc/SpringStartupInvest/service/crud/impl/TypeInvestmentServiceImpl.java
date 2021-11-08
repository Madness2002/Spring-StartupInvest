package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pe.edu.upc.SpringStartupInvest.model.entity.TypeInvestment;
import pe.edu.upc.SpringStartupInvest.model.repository.TypeInvestmentRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.TypeInvestmentService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeInvestmentServiceImpl implements TypeInvestmentService {

	@Autowired
	private TypeInvestmentRepository typeInvestmentRepository;

	@Override
	public JpaRepository<TypeInvestment, Integer> getJpaRepository() {
		return typeInvestmentRepository;
	}
	
	@Override
	public List<TypeInvestment> findByName(String name) throws Exception{
		return typeInvestmentRepository.findByName(name);
	}
	
}
