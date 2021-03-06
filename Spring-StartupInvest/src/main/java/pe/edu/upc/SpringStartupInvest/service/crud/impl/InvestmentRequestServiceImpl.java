package pe.edu.upc.SpringStartupInvest.service.crud.impl;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestmentRequest;
import pe.edu.upc.SpringStartupInvest.model.repository.InvestmentRequestRepository;
import pe.edu.upc.SpringStartupInvest.service.crud.InvestmentRequestService;

import java.util.List;

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

	@Override
	public List<InvestmentRequest> findInvestmentRequestByStartupId(Integer startupId) {
		// TODO Auto-generated method stub
		return investmentRequestRepository.findInvestmentRequestByStartupId(startupId);
	}

	@Override
	public double getAmountColectedByStartupId(int id) throws Exception {
		return investmentRequestRepository.getAmountColectedByStartupId(id);
	}

	@Override
	public double getAmountColectedByInvestmentRequestId(Integer id) throws Exception {
		return investmentRequestRepository.getAmountColectedByInvestmentRequestId(id);
	}

	@Override
	public Integer getInvestorQuantityByInvestmentRequestId(Integer id) throws Exception {

		return investmentRequestRepository.getInvestorQuantityByInvestmentRequestId(id);
	}

}
