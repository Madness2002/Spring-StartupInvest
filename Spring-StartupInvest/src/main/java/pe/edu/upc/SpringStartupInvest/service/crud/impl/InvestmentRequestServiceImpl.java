package pe.edu.upc.SpringStartupInvest.service.crud.impl;




import pe.edu.upc.SpringStartupInvest.model.entity.InvestmentRequest;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestmentRequestRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestmentRequestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class InvestmentRequestServiceImpl implements InvestmentRequestService {

	@Autowired
	private InvestmentRequestRepository investmentRequestRepository;

	@Override
	public JpaRepository<InvestmentRequest, Integer> getJpaRepository() {
		return investmentRequestRepository;
	}


	
}
