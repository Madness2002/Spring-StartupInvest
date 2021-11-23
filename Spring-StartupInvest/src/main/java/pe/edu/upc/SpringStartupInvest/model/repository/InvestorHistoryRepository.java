package pe.edu.upc.SpringStartupInvest.model.repository;

import org.springframework.stereotype.Repository;

import pe.edu.upc.SpringStartupInvest.model.entity.InvestorHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface InvestorHistoryRepository extends JpaRepository<InvestorHistory, Integer> {

	@Query(value = "select * from listInvestments",nativeQuery = true)
	List<InvestorHistory> listPortafolio();

	
	
	@Query(value = "select ih.investor_history_id,ih.investor_history_amount,ih.investor_history_date,ih.investor_history_state,ih.investment_request_id,ih.investor_id,ih.type_card_id \r\n"
			+ "from investor_histories ih join investment_requests ir on ih.investment_request_id= ir.investment_request_id\r\n"
			+ "where ir.investment_request_return_date<current_date and ih.investor_id=?1 and ih.investor_history_state=true", nativeQuery = true)
	List<InvestorHistory> listInvestmentsToReclaimByInvestorId(Integer id);
	
}
