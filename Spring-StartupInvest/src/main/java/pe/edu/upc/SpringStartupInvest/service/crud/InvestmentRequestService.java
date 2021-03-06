package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestmentRequest;

public interface InvestmentRequestService extends CrudService<InvestmentRequest, Integer> {
	List<InvestmentRequest> findInvestmentRequestByStartupId(Integer startupId);

	double getAmountColectedByStartupId(int id) throws Exception;
	
	double getAmountColectedByInvestmentRequestId(Integer id) throws Exception;
	Integer getInvestorQuantityByInvestmentRequestId(Integer id) throws Exception;
}
