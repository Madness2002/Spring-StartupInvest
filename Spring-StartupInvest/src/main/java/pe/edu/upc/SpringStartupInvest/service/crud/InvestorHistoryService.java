package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;



public interface InvestorHistoryService extends CrudService<InvestorHistory, Integer> {

	@Transactional(readOnly = true)
	List<InvestorHistory> listPortafolio();
	
	
	List<InvestorHistory> listPortafolioByInvestor(Integer id);
	
	
	List<InvestorHistory> listInvestmentsToReclaimByInvestorId(Integer id);
	
}
