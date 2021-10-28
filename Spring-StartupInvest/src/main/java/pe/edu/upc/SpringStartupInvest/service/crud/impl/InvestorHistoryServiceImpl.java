package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestorHistoryRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestorHistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InvestorHistoryServiceImpl implements InvestorHistoryService {

	@Autowired
	private InvestorHistoryRepository investorHistoryRepository;

	@Override
	public JpaRepository<InvestorHistory, Integer> getJpaRepository() {
		return investorHistoryRepository;
	}

	
}
