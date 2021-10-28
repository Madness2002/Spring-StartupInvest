package pe.edu.upc.SpringStartupInvest.service.crud.impl;


import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestorRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InvestorServiceImpl implements InvestorService {

	@Autowired
	private InvestorRepository investorRepository;

	@Override
	public JpaRepository<Investor, Integer> getJpaRepository() {
		return investorRepository;
	}

	
}
