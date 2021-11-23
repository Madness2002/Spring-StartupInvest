package pe.edu.upc.SpringStartupInvest.service.crud.impl;


import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestorRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorService;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Optional<Investor> findById(Integer id) {
		
		return investorRepository.findById(id);
	}

	@Override
	public Optional<Investor> findInvestorByInvestorHistoryId(Integer id) {
		return investorRepository.findInvestorByInvestorHistoryId(id);
	}

	@Override
	public Integer getTotalTimesInvestedByInvestorId(Integer id) {
		
		return investorRepository.getTotalTimesInvestedByInvestorId(id);
	}

	@Override
	public double getAmountTotalInvestedByInvestorId(Integer id) {
		
		return investorRepository.getAmountTotalInvestedByInvestorId(id);
	}

	@Override
	public Optional<Investor> findByName(String name) {
		
		return investorRepository.findByName(name);
	}

	

	@Override
	public double getAmountTotalToReclaimByInvestorId(Integer id) {
		return investorRepository.getAmountTotalToReclaimByInvestorId(id);
	}

	@Override
	public double getAmountTotalToReclaimedByInvestorId(Integer id) {
		
		return investorRepository.getAmountTotalToReclaimedByInvestorId(id);
	}

	
	
	
	
	
}
