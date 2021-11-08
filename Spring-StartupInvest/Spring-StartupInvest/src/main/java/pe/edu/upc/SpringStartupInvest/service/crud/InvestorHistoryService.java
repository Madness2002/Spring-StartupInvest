package pe.edu.upc.SpringStartupInvest.service.crud;

import java.util.List;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;



public interface InvestorHistoryService extends CrudService<InvestorHistory, Integer> {

	
	List<InvestorHistory> listPortafolioByInvestorId(Integer id);
}
