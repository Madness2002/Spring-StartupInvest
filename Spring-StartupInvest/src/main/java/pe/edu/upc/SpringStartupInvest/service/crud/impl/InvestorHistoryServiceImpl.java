package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import pe.edu.upc.SpringStartupInvest.model.entity.Investor;
import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestorHistoryRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorHistoryService;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InvestorHistoryServiceImpl implements InvestorHistoryService {

	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private InvestorHistoryService investorHistoryService;
	
	@Autowired
	private InvestorHistoryRepository investorHistoryRepository;

	@Override
	public JpaRepository<InvestorHistory, Integer> getJpaRepository() {
		return investorHistoryRepository;
	}

	@Override
	public List<InvestorHistory> listPortafolio() {
		
		return investorHistoryRepository.listPortafolio();
	}

	@Override
	public List<InvestorHistory> listPortafolioByInvestor(Integer id) {
		List<InvestorHistory> investments = new ArrayList<>();
		List<InvestorHistory> investmentsByInvestor = new ArrayList<>();
		investments = investorHistoryService.listPortafolio();

		for (InvestorHistory investment : investments) {
			
		Integer idInvestmentHistory = investment.getId();
		Optional<Investor> investor= investorService.findInvestorByInvestorHistoryId(idInvestmentHistory) ;
		investment.setInvestor(investor.get());
		
		if(investment.getInvestor().getId().equals(id)) {
			investmentsByInvestor.add(investment);	
		}
		
		}	
		return investmentsByInvestor;
	}

	
	@Override
	public List<InvestorHistory> listInvestmentsToReclaimByInvestorId(Integer id) {
		return investorHistoryRepository.listInvestmentsToReclaimByInvestorId(id);
	}

}
